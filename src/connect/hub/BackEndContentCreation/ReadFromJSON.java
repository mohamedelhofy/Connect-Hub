/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import connect.hub.BackEndContentCreation.Post;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadFromJSON {

    private final Path filePath;

    public ReadFromJSON(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public List<Map<String, Object>> getDataAsListOfMaps() {
        List<Map<String, Object>> dataList = new ArrayList<>();

        try {
            if (Files.exists(filePath)) {
                String jsonContent = Files.readString(filePath);

                try {
                    JSONArray jsonArray = new JSONArray(jsonContent);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Map<String, Object> dataMap = new HashMap<>();

                        try {
                            dataMap.put("contentId", jsonObject.getString("contentId"));
                            dataMap.put("authorId", jsonObject.getString("autherId"));
                            dataMap.put("content", jsonObject.getString("content"));
                            dataMap.put("timestamp", jsonObject.getString("timestamp"));

                            if (jsonObject.has("image")) {
                                dataMap.put("image", jsonObject.getString("image"));
                            }

                            dataList.add(dataMap);
                        } catch (JSONException | DateTimeParseException e) {
                            System.err.println("Skipping invalid data entry: " + e.getMessage());
                        }
                    }
                } catch (JSONException e) {
                    System.err.println("Invalid JSON structure: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return dataList;
    }

    public List<Post> getPostList() {
        List<Map<String, Object>> dataList = getDataAsListOfMaps();  // Get the data list as maps
    List<Post> posts = new ArrayList<>();

    for (Map<String, Object> dataMap : dataList) {
        String contentId = (String) dataMap.get("contentId");
        String authorId = (String) dataMap.get("authorId");
        String contentText = (String) dataMap.get("content");
        String timestampString = (String) dataMap.get("timestamp");
        System.out.println(timestampString);
        // Set default timestamp to now, or parse the timestamp string if it exists
        LocalDateTime timestamp = LocalDateTime.now();  // default to now if no timestamp is provided
        if (timestampString != null && !timestampString.isEmpty()) {
            try {
                timestamp = LocalDateTime.parse(timestampString);  // Parse timestamp string to LocalDateTime
            } catch (DateTimeParseException e) {
                System.err.println("Invalid timestamp format: " + e.getMessage());
            }
        }

        // Check for the image field in the map, and decode it from Base64 if it exists
        String imageBase64 = (String) dataMap.get("image");
        Image image = null;
        if (imageBase64 != null && !imageBase64.isEmpty()) {
            image = decodeBase64ToImage(imageBase64);
        }
        Post post = new Post(authorId, contentText, image);
        post.setContentID(contentId);
        post.setTimeStamp(timestamp);

        posts.add(post);
    }

    return posts;
    }

    private Image decodeBase64ToImage(String base64) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(byteArrayInputStream);
        } catch (IOException e) {
            System.err.println("Error decoding Base64 image: " + e.getMessage());
            return null;
        }
    }
}
