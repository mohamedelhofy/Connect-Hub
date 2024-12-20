/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author رحمه صبرى
 */
public class Post implements SocialContent {

    private String contentId;
    private final String authorId;
    private String contentText;
    private Image image;
    private LocalDateTime timestamp = LocalDateTime.now();
    private static int storyInctanceCount = 0;
    private List<Map<String, String>> comments;
    private List<String> likes;

    public Post(String authorId, String contentText, Image image) {
        this.authorId = authorId;
        this.contentText = contentText;
        this.image = image;
        setContentId();
    }

    @Override
    public void setContentId() {
        int counter = storyInctanceCount++;
        contentId = "P0" + counter;
    }

    public void setContentID(String contentID) {
        contentId = contentID;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public void setTimeStamp(LocalDateTime time) {
        try {
            // Parse the string to LocalDateTime
            timestamp = time;
        } catch (Exception e) {
            System.err.println("Invalid timestamp format: " + e.getMessage());
        }
    }

    public void setComment(String userIdFriend, String comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }

        Map<String, String> commentMap = new HashMap<>();
        commentMap.put("userId", userIdFriend);
        commentMap.put("comment", comment);
        comments.add(commentMap);
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }
    
    public void setLike(String userIdFriend){
        if (likes == null) {
            likes = new ArrayList<>();
        }
        likes.add(userIdFriend);
    }
    public int getNumOfLikes(){
        return likes.size();
    }

    public List<String> getLikes() {
        return likes;
    }
    public int getSizeLike(){
        return likes.size();
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Image getImage() {
        return image;
    }
    
    
}
