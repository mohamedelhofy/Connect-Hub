/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author رحمه صبرى
 */
public class RemoveFromJSON {

    private String autherId;
    private String contentId;
    private String filePath;

    public RemoveFromJSON(String autherId, String contentId, SocialContent content, String filePath) {
        this.autherId = autherId;
        this.contentId = contentId;
        this.filePath = filePath;
    }

    public void removeFromJSON() {
        Path selectedfilePath = Paths.get(filePath);
        try {
            String jsonContent = Files.readString(selectedfilePath);
            JSONArray rootArray = new JSONArray(jsonContent);
            JSONArray filteredArray = new JSONArray();

            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject jsonObject = rootArray.getJSONObject(i);

                if (jsonObject.has("contentId") && jsonObject.has("autherId")) {
                    String selectedAutherId = jsonObject.getString("autherId");
                    String selectedContentId = jsonObject.getString("contentId");
                    if (selectedAutherId == this.autherId && selectedContentId == this.contentId) {
                        filteredArray.put(jsonObject);
                    }
                } else {
                    filteredArray.put(jsonObject);
                }
            }
            Files.write(selectedfilePath, filteredArray.toString(4).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }

}
