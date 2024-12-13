/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupFront;

import GroupClass.GroupPrimaryAdmin;
import connect.hub.BackEndContentCreation.Post;
import connect.hub.BackEndContentCreation.ReadFromJSON;
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
public class DeletePost  {

    public DeletePost(GroupPrimaryAdmin group,boolean flag) {

        JFrame frame = new JFrame("Delete Post GUI");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel textLabel1 = new JLabel("Post ID:");
        JTextField textField1 = new JTextField();



        inputPanel.add(textLabel1);
        inputPanel.add(textField1);

        JButton savePost = new JButton("Save");
        savePost.setFont(new Font("Arial", Font.PLAIN, 18));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(savePost,  BorderLayout.SOUTH);

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
                        group.removePost(post);
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
