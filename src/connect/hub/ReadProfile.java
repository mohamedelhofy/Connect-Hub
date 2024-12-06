/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;
import connect.hub.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Compu City
 */
public class ReadProfile {
    private final Path filePath ;
    
    public ReadProfile(String fileName) {
        this.filePath = Paths.get(fileName);
    }
    public List<Map<String, Object>> getDataAsListOfMaps()  {
        List<Map<String, Object>> dataList = new ArrayList<>();
        try{
            if (Files.exists(filePath)) {
                String jsonContent = Files.readString(filePath);
                JSONArray jsonArray = new JSONArray(jsonContent);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("userId", jsonObject.getString("userId"));
                    dataMap.put("bio", jsonObject.getString("bio"));
                    if (jsonObject.has("profile photo")) {
                        dataMap.put("profile photo", jsonObject.getString("profile photo"));
                    }
                    if (jsonObject.has("cover photo")) {
                        dataMap.put("cover photo", jsonObject.getString("cover photo"));
                    }
                    dataList.add(dataMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        System.out.println("Error parsing JSON: " + e.getMessage());
        }
        return dataList;
    }
}
