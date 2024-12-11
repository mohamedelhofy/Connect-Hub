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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add space between components

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose();
            }
        });

        // Header Panel - Cover Photo
        JPanel coverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image coverImage;
                if (data != null)
                    coverImage = Toolkit.getDefaultToolkit().getImage((String) data.get("cover photo"));
                else
                    coverImage = Toolkit.getDefaultToolkit().getImage("default_cover.jpg");
                g.drawImage(coverImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        coverPanel.setPreferredSize(new Dimension(400, 200));
        add(coverPanel, BorderLayout.NORTH);

 
        JPanel profilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image profileImage;
                if (data != null)
                    profileImage = Toolkit.getDefaultToolkit().getImage((String) data.get("profile photo"));
                else
                    profileImage = Toolkit.getDefaultToolkit().getImage("default_profile.jpg");
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(new Ellipse2D.Float(100, 10, 200, 200)); 
                g2d.drawImage(profileImage, 100, 10, 200, 200, this);
            }
        };
        profilePanel.setPreferredSize(new Dimension(400, 200));
        add(profilePanel, BorderLayout.CENTER);

        // Bio Section with line separators and styling
        JPanel bioPanel = new JPanel();
        bioPanel.setLayout(new BorderLayout(5, 5)); 
        bioPanel.setBorder(BorderFactory.createTitledBorder("Bio"));
        bioPanel.setBackground(new Color(245, 245, 245));

        JTextArea bioText = new JTextArea();
        bioText.setEditable(false);
        bioText.setLineWrap(true);
        bioText.setWrapStyleWord(true);
        bioText.setFont(new Font("Arial", Font.PLAIN, 14));
        bioText.setText(data != null && data.get("bio") != null ? (String) data.get("bio") : "No bio available.");
        bioText.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JScrollPane bioScrollPane = new JScrollPane(bioText);
        bioPanel.add(bioScrollPane, BorderLayout.CENTER);

        // Buttons panel with styling and proper spacing
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10)); 

        JButton editBioButton = new JButton("Edit Profile");
        JButton editPassButton = new JButton("Change Password");
        editBioButton.setBackground(new Color(36, 48, 69));
        editPassButton.setBackground(new Color(36, 48, 69));
        editBioButton.setForeground(Color.WHITE);
        editPassButton.setForeground(Color.WHITE);
        editBioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        editPassButton.setFont(new Font("Arial", Font.PLAIN, 14));

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

       
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(bioPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);
        
    }

}

