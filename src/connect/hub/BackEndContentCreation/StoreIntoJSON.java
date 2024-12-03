/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author رحمه صبرى
 */
public class StoreIntoJSON{
    private final SocialContent content;

    public StoreIntoJSON(SocialContent content) {
        this.content = content;
    }
    
    public void addPostsToJSON() throws IOException, JSONException {
        Path filePath = Paths.get("PostsDB.json");
        addToJSON(filePath);
    }

    public void addStoriesToJSON() throws IOException, JSONException {
        Path filePath = Paths.get("StoriesDB.json");
        addToJSON(filePath);
    }
    
    
    public void addToJSON(Path filePath) throws IOException, org.json.JSONException {
        JSONArray jsonArray;
        if (Files.exists(filePath)) {
            String jsonContent = Files.readString(filePath);
            jsonArray = new JSONArray(jsonContent);
        } else {
            jsonArray = new JSONArray();
        }
        JSONObject newEntry = new JSONObject();
        newEntry.put("contentId", content.getContentId());
        newEntry.put("autherId", content.getAuthorId());
        newEntry.put("content", content.getContentText());
        newEntry.put("timestamp", content.timestamp().toString());
        if (content.getOptinalImage() != null) {
            newEntry.put("image", imageToBase64());
        }
        jsonArray.put(newEntry);
        Files.write(filePath, jsonArray.toString(4).getBytes());
    }

    private String imageToBase64() throws IOException {
        if (content.getOptinalImage() != null) {
            BufferedImage bufferedImage = (BufferedImage) content.getOptinalImage();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        }
        return null;
    }
}
    

