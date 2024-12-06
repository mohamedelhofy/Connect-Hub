/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connect.hub;

/**
 *
 * @author Compu City
 */
public class ConnectHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws org.json.JSONException {
        UserDataActions userDataActions = new FileDataActions();
        Hashing passwordHasher = new Hashing();
        UserServices userServices = new UserServices(userDataActions, passwordHasher);
        LoginWindow loginWindow = new LoginWindow(userServices);
        loginWindow.setVisible(true);    
    }
    
}
