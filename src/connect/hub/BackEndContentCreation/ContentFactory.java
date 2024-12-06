/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import java.awt.Image;

/**
 *
 * @author رحمه صبرى
 */
public interface ContentFactory {
    SocialContent createContent(String authorId, String contentText, Image image);
}
