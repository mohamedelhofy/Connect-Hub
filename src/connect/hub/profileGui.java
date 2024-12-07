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
    User user=User.getInstance();
     

    ProfilesData dataProfile=new ProfilesData(user.getUserId());
    Map<String, Object> data=dataProfile.getData();

    public profileGui() {
        setTitle("Profile App");
        setSize(400, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new NewsfeedPage().setVisible(true);
                dispose(); 
            }
        });
        JPanel coverPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image coverImage = Toolkit.getDefaultToolkit().getImage((String)data.get("cover photo"));
                g.drawImage(coverImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        coverPanel.setPreferredSize(new Dimension(400, 200));
        add(coverPanel, BorderLayout.NORTH);
        
        JPanel profilePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image profileImage = Toolkit.getDefaultToolkit().getImage((String)data.get("profile photo"));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setClip(new Ellipse2D.Float(100, 10, 200, 200)); // Circular crop
                g2d.drawImage(profileImage, 100, 10, 200, 200, this);
            }
        };
        profilePanel.setPreferredSize(new Dimension(400, 150));
        add(profilePanel, BorderLayout.CENTER);
        
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        JButton editBioButton = new JButton("Edit profile");
        JButton editpassButton = new JButton("Edit password");

        editBioButton.addActionListener(e-> {
            this.dispose();
            new ProfileManagementPage().setVisible(true);
        });
        

        editpassButton.addActionListener(e-> {
            this.dispose();
            new ChangePass().setVisible(true);
        });


        buttonPanel.add(editBioButton);
        buttonPanel.add(editpassButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
    
}
