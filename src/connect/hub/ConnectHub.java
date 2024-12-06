
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package connect.hub;

import connect.hub.BackEndContentCreation.FriendManager;

/**
 *
 * @author Compu City
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        FriendManager friendManager = FriendManager.getInstance();
        friendManager.sendFriendRequest("us", "ur");
    }
}
