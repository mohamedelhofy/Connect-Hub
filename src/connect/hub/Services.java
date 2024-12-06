/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package connect.hub;

/**
 *
 * @author rawan
 */
public interface Services {
public void signUp(User user);  
User login(String email, String password); 
public void logout(User user);  
}
