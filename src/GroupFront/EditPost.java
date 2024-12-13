/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.GroupAdmin;
import GroupClass.GroupPrimaryAdmin;
import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.ReadFromJSON;
import connect.hub.NewsfeedPage;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.JSONException;

/**
 *
 * @author Compu City
 */
public class EditPost {
    Image image;
    public EditPost(GroupPrimaryAdmin group,boolean flag) {
            

        JFrame frame = new JFrame("Edit Post GUI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel textLabel1 = new JLabel("Post ID:");
        JTextField textField1 = new JTextField();

        JLabel textLabel2 = new JLabel("New text:");
        JTextField textField2 = new JTextField();

        JButton chooseImageButton = new JButton("Choose Image");
        JLabel imageLabel = new JLabel("No image selected", JLabel.CENTER);
        inputPanel.add(textLabel1);
        inputPanel.add(textField1);
        inputPanel.add(textLabel2);
        inputPanel.add(textField2);
        inputPanel.add(chooseImageButton);
        JButton savePost = new JButton("Save");
        savePost.setFont(new Font("Arial", Font.PLAIN, 18));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(savePost,  BorderLayout.SOUTH);
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageLabel.setText(""); 
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(image));
                }
            }
        });
        frame.add(panel);

        frame.setVisible(true);
    

        savePost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadFromJSON readFromJSON=new ReadFromJSON("PostDB.json");
                List<Map<String, Object>> dataList=null ;
                try {
                    dataList =readFromJSON.getDataAsListOfMaps();
                } catch (IOException ex) {
                    Logger.getLogger(GroupAdminGui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(GroupAdminGui.class.getName()).log(Level.SEVERE, null, ex);

                }
                for(Map<String, Object> data :  dataList){
                    if(data.get("contentId")==textField1.getText()){ 
                        Post post=new Post((String)data.get("authorId"),(String)textField1.getText(),(Image)data.get("image"));
                        group.editPost(post, textField2.getText(), image);
                    }
                }
                if(flag==true)
                    new GroupAdminGui(group).setVisible(true);
                frame.dispose();
            }
        }); 
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(flag==true)
                    new GroupAdminGui(group).setVisible(true);
                frame.dispose();
            }
        });
    }
}


