
import connect.hub.User;
import java.io.FileWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chatFileHandler {
    
    ChatBackend chatbackend = new ChatBackend();
    private static final String FILE_PATH = "chat.json";

    // Reads the JSON file and returns the JSONObject
    public  JSONObject readJsonFile() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        try {
            return new JSONObject(content);
        } catch (JSONException ex) {
            Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Error reading JSON file", ex);  
        }
    }
   public  void writeJsonFile(List<User> users, List<Message> messages) throws IOException, JSONException {
        // Create a JSONObject to hold the entire chat data
        JSONObject jsonObject = new JSONObject();

        // Convert the list of users to a JSONArray
        JSONArray usersArray = new JSONArray();
        for (User user : users) {
            JSONObject userObject = new JSONObject();
            userObject.put("id", user.getUserId());
            userObject.put("name", user.getUsername());

            // Convert the list of friends to a JSONArray
            ////the method which view the friends of user 
           // JSONArray friendsArray = new JSONArray(user.getFriends());
        //    userObject.put("friends", friendsArray);

            usersArray.put(userObject);
        }

        // Add users array to the root JSONObject
        jsonObject.put("users", usersArray);

        // Convert the list of messages to a JSONArray
        JSONArray messagesArray = new JSONArray();
        for (Message message : messages) {
            JSONObject messageObject = new JSONObject();
            try {
                messageObject.put("sender_id", message.getSender().getUserId());
            } catch (JSONException ex) {
                Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            messageObject.put("receiver_id", message.getReciever().getUserId());
            try {
                messageObject.put("content", message.getMessageContent());
            } catch (JSONException ex) {
                Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                messageObject.put("timestamp", message.getTimeOfMessage().toString());
            } catch (JSONException ex) {
                Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            messagesArray.put(messageObject);
        }

        try {
            // Add messages array to the root JSONObject
            jsonObject.put("messages", messagesArray);
        } catch (JSONException ex) {
            Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Write the JSON object to the file
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonObject.toString(4));  
        } catch (JSONException ex) {
            Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}