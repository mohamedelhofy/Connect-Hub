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
public class StoriesFactory implements ContentFactory{
    public SocialContent createContent(String authorId, String contentText, Image image) {
        return new Stories(authorId, contentText, image);
    }
}
