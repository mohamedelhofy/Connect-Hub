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
public class Post implements SocialContent{
    private String contentId;
    private String authorId;
    private String contentText;
    private Image image ;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private static int storyInctanceCount = 0;

    public Post( String authorId, String contentText, Image image) {
        this.authorId = authorId;
        this.contentText = contentText;
        this.image = image;
    }

    @Override
    public void setContentId() {
        int counter=storyInctanceCount++;
        contentId = "P0"+counter;
    }
    @Override
    public String getContentText() {
        
        return contentText;
    }

    @Override
    public Image getOptinalImage() {
        return image;
    }

    @Override
    public String getContentId() {
        setContentId();
        return contentId;
    }

    @Override
    public String getAuthorId() {
        return authorId;
    }

    @Override
    public LocalDateTime timestamp() {
        return timestamp;
    }
}
