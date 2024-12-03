/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import java.awt.Image;
import java.time.LocalDateTime;

/**
 *
 * @author رحمه صبرى
 */
public interface SocialContent {
    void setContentId();
    String getContentId();
    String getAuthorId();
    String getContentText(); 
    Image getOptinalImage();
    LocalDateTime timestamp();
}
