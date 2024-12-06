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
public class StoreIntoJSON {

    private final SocialContent content;

    public StoreIntoJSON(SocialContent content) {
        this.content = content;
    }

    public void addPostsToJSON() {
        Path filePath = Paths.get("PostsDB.json");

        try {
            addToJSON(filePath);
        } catch (IOException | JSONException e) {
            System.err.println("An error occurred while adding posts to JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addStoriesToJSON() {
        Path filePath = Paths.get("StoriesDB.json");

        try {
            addToJSON(filePath);
        } catch (IOException | JSONException e) {
            System.err.println("An error occurred while adding posts to JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addToJSON(Path filePath) throws IOException, org.json.JSONException {
        JSONArray jsonArray;

        if (Files.exists(filePath)) {
            String jsonContent = Files.readString(filePath);

            try {
                // Attempt to parse the content as a JSONArray
                jsonArray = new JSONArray(jsonContent);
            } catch (JSONException e) {
                // If parsing fails, log the error and create a new JSONArray
                System.err.println("Invalid JSON format detected. Creating a new JSON array.");
                jsonArray = new JSONArray();
            }
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

        // Add the new entry to the JSONArray
        jsonArray.put(newEntry);

        // Write the updated JSONArray back to the file with indentation for readability
        Files.write(filePath, jsonArray.toString(4).getBytes());
    }

    private String imageToBase64() {
        try {
            // Check if the image is of type BufferedImage
            if (content.getOptinalImage() instanceof BufferedImage) {
                BufferedImage bufferedImage = (BufferedImage) content.getOptinalImage();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                return Base64.getEncoder().encodeToString(imageBytes);
            } else {
                System.err.println("Error: The image is not of type BufferedImage.");
            }
        } catch (IOException e) {
            // Handle and log IOException that may occur during image processing
            System.err.println("Error occurred while converting image to Base64: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Return null if there's no valid image or an error occurs

    }
}
