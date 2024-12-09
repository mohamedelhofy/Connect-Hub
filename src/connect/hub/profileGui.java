/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.util.Map;
/**
 *
 * @author Compu City
 */
public class profileGui extends JFrame {
    User user = User.getInstance();
    ProfilesData dataProfile = new ProfilesData(user.getUserId());
    Map<String, Object> data = dataProfile.getData();

    public profileGui() {
        setTitle("Profile App");
        setSize(400, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose();
            }
        });

        // Cover photo panel
        JPanel coverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image coverImage;
                if (data != null)
                    coverImage = Toolkit.getDefaultToolkit().getImage((String) data.get("cover photo"));
                else
                    coverImage = Toolkit.getDefaultToolkit().getImage("D:\\\\OOP\\\\Connect-Hub\\\\my_photo\\\\white.jpg");
                g.drawImage(coverImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        coverPanel.setPreferredSize(new Dimension(400, 200));
        add(coverPanel, BorderLayout.NORTH);

        // Profile photo panel
        JPanel profilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image profileImage;
                if (data != null)
                    profileImage = Toolkit.getDefaultToolkit().getImage((String) data.get("profile photo"));
                else
                    profileImage = Toolkit.getDefaultToolkit().getImage("D:\\\\OOP\\\\Connect-Hub\\\\my_photo\\\\panda.jpg");
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(new Ellipse2D.Float(100, 10, 200, 200)); // Circular crop
                g2d.drawImage(profileImage, 100, 10, 200, 200, this);
            }
        };
        profilePanel.setPreferredSize(new Dimension(400, 200));
        add(profilePanel, BorderLayout.CENTER);

        // Bio and buttons panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        // Bio panel
        JPanel bioPanel = new JPanel();
        bioPanel.setLayout(new BorderLayout());
        JLabel bioLabel = new JLabel("Bio:");
        bioLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea bioText = new JTextArea();
        bioText.setEditable(false);
        bioText.setLineWrap(true);
        bioText.setWrapStyleWord(true);
        bioText.setFont(new Font("Arial", Font.PLAIN, 12));
        bioText.setText(data != null && data.get("bio") != null ? (String) data.get("bio") : "No bio available.");

        JScrollPane bioScrollPane = new JScrollPane(bioText);
        bioScrollPane.setPreferredSize(new Dimension(380, 80));

        bioPanel.add(bioLabel, BorderLayout.NORTH);
        bioPanel.add(bioScrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton editBioButton = new JButton("Edit profile");
        JButton editPassButton = new JButton("Edit password");

        editBioButton.addActionListener(e -> {
            this.dispose();
            new ProfileManagementPage().setVisible(true);
        });

        editPassButton.addActionListener(e -> {
            this.dispose();
            new ChangePass().setVisible(true);
        });

        buttonPanel.add(editBioButton);
        buttonPanel.add(editPassButton);

        // Combine bio and buttons in southPanel
        southPanel.add(bioPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
    }
}
