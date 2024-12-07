/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect.hub;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Compu City
 */
public class SaveProfile {
    private String filePath;
    private List<Map<String, Object>> datalist;
    public SaveProfile( String filePath,List<Map<String, Object>> datalist) {
        this.filePath = filePath;
        this.datalist=datalist;
    }
     public void savaFromJSON() {
    Path selectedfilePath = Paths.get(filePath);
    try {
        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> dataMap : datalist) {
            JSONObject jsonObject = new JSONObject(dataMap);
            jsonArray.put(jsonObject);
        }
        Files.write(selectedfilePath, jsonArray.toString(4).getBytes());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        System.out.println("Error parsing JSON: " + e.getMessage());
    }
    }
}
