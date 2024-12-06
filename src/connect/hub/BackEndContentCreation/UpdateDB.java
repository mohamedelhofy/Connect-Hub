/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub.BackEndContentCreation;

import connect.hub.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author رحمه صبرى
 */
public class UpdateDB extends Thread{
    private String filePath;

    public UpdateDB(String filePath) {
        this.filePath = filePath;
    }
    
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
               update();
                Thread.sleep(1000 ); 
            }
        } catch (InterruptedException e) {
            
        }
    }
    
    public void update(){
        Path selectedfilePath = Paths.get(filePath);
        try {
            String jsonContent = Files.readString(selectedfilePath);
            JSONArray rootArray = new JSONArray(jsonContent);
            JSONArray filteredArray = new JSONArray();

            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject jsonObject = rootArray.getJSONObject(i);

                if (jsonObject.has("timestamp")) {
                    String timestamp = jsonObject.getString("timestamp");
                    LocalDateTime convertedTimestamp = LocalDateTime.parse(timestamp);
                    LocalDateTime now = LocalDateTime.now();
                    Duration duration = Duration.between(convertedTimestamp, now);
                    if (duration.toHours() < 24) {
                        filteredArray.put(jsonObject);
                    }
                } else {
                    filteredArray.put(jsonObject);
                }
                Files.write(selectedfilePath, filteredArray.toString(4).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
