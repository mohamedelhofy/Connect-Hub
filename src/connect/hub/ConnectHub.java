/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connect.hub;

import connect.hub.FrontEndContentCreation.FriendManagementInterface;
import connect.hub.FrontEndContentCreation.NewPostGUI;
import connect.hub.FrontEndContentCreation.NewStoryGUI;
import connect.hub.FrontEndContentCreation.PostScrollingPage;
import connect.hub.FrontEndContentCreation.StoryScrollingPage;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Compu City
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws org.json.JSONException {

//        UserServices userServices = new UserServices();
//        LoginWindow loginWindow = new LoginWindow(userServices);
//        loginWindow.setVisible(true);  
//            new NewsfeedPage().setVisible(true);
         //   new profileGui().setVisible(true);
         FriendManagementInterface friendGUI = new FriendManagementInterface();
         friendGUI.showFrame();
    }
    
}

