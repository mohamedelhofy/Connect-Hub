/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author رحمه صبرى
 */
public class readGroupFromJSON {
    public List<Map<String, Object>> getGroupListDB() throws IOException, org.json.JSONException {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Path filePath = Path.of("Group.json");
        if (Files.exists(filePath)) {
            String jsonContent = Files.readString(filePath);
            JSONArray jsonArray = new JSONArray(jsonContent);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("groupName", jsonObject.getString("groupName"));
                dataMap.put("groupDescription", jsonObject.getString("groupDescription"));
                dataMap.put("groupPhotoPath", jsonObject.getString("groupPhotoPath"));
                dataMap.put("subscribedUser", jsonObject.getString("subscribedUser"));
                dataMap.put("postsId", jsonObject.getString("postsId"));
                dataList.add(dataMap);
            }
        }
        return dataList;
    }
}
