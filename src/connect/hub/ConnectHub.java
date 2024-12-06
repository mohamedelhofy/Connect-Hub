/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connect.hub;
import javax.swing.SwingUtilities;
import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.NewStoryGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.FrontEndContentCreation.StoryScrollingPage;
import javax.swing.*;

/**
 *
 * @author Compu City
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("My test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 400);
        JButton NewPost = new JButton("New Post");
        NewPost.setBounds(20, 50, 100, 30);
        JButton NewStory = new JButton("New Story");
        NewStory.setBounds(150, 50, 100, 30);
        JButton myStory = new JButton("My Story");
        myStory.setBounds(300, 50, 100, 30);
        JButton myPosts = new JButton("My Posts");
        myPosts.setBounds(450, 50, 100, 30);
        NewPost.addActionListener(e->{
            NewPostGUI newPostFrame = new NewPostGUI("User01");
            newPostFrame.newPostFrame();
            frame.setVisible(false);
        });
        NewStory.addActionListener(e->{
        NewStoryGUI newStoryFrame = new NewStoryGUI("User02");
        newStoryFrame.newStoryFrame();
        });
        myPosts.addActionListener(e->{
        PostScrollingPage.createAndShowGUI();
        });
        myStory.addActionListener(e->{
        StoryScrollingPage.createAndShowGUI();
        });
        frame.add(NewPost);
        frame.add(NewStory);
        frame.add(myStory);
        frame.add(myPosts);
        frame.setVisible(true);
        
        
    }
    
}
