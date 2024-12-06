package connect.hub.BackEndContentCreation;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**
 *
 * @author
 */
public class WriteFriendRequestsToJSON {
    private final Path filePath = Paths.get("Friend Requests.json");

    // Method to write the list of maps to the JSON file
    public void writeFromListOfMaps(ArrayList<Map<String, String>> friendRequestsList) {
        JSONArray jsonArray = new JSONArray();

        // Convert each map in the list to a JSONObject and add it to the JSONArray
        for (Map<String, String> request : friendRequestsList) {
            JSONObject jsonObject = new JSONObject(request);
            jsonArray.put(jsonObject);
        }

        try {
            // Write the JSONArray to the file
            Files.writeString(filePath, jsonArray.toString(4)); // 4 for pretty printing
            System.out.println("Friend requests successfully written to JSON file.");
        } catch (JSONException ex) {
            JOptionPane.showMessageDialog(null, "Error processing JSON", "Message", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException i){
            JOptionPane.showMessageDialog(null, "Error Writing to File", "Message", JOptionPane.ERROR_MESSAGE);
        }
    }
}
