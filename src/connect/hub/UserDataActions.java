/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package connect.hub;

/**
 *
 * @author rawan
 */
public interface UserDataActions {
    public void  save(User user);
    public User findByEmail(String email);
    public void updateUser(User user);
}
