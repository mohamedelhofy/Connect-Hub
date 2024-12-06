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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author رحمه صبرى
 */
public class ReadFromJSON {

    private final Path filePath;

    public ReadFromJSON(String fileName) {
        this.filePath = Paths.get(fileName);
    }

    public List<Map<String, Object>> getDataAsListOfMaps() throws IOException, org.json.JSONException {
        List<Map<String, Object>> dataList = new ArrayList<>();

        if (Files.exists(filePath)) {
            String jsonContent = Files.readString(filePath);
            JSONArray jsonArray = new JSONArray(jsonContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("contentId", jsonObject.getString("contentId"));
                dataMap.put("authorId", jsonObject.getString("autherId"));
                dataMap.put("content", jsonObject.getString("content"));
                dataMap.put("timestamp", LocalDateTime.parse(jsonObject.getString("timestamp")));
                if (jsonObject.has("image")) {
                    dataMap.put("image", jsonObject.getString("image"));
                }

                dataList.add(dataMap);
            }
        }
        return dataList;
    }
}
