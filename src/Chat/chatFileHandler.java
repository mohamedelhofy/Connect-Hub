package Chat;

import Chat.Message;
import connect.hub.User;
import java.io.BufferedReader;
import java.io.FileReader;
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
    
 
    private static final String FILE_PATH = "chat.json";

    public static List<Message> readJsonFile() throws IOException, JSONException {
        List<Message> messages = new ArrayList<>();

        // Read the JSON file content
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        }

        // Parse the JSON content
        JSONObject jsonObject = new JSONObject(contentBuilder.toString());

        // Extract messages array
        JSONArray messagesArray = jsonObject.getJSONArray("messages");

        // Convert each JSONObject in the array to a Message object
        for (int i = 0; i < messagesArray.length(); i++) {
            JSONObject messageObject = messagesArray.getJSONObject(i);

            // Create a Message object (assuming appropriate constructor or setters exist)
            Message message = new Message(messageObject.getString("sender_id"),messageObject.getString("receiver_id"),messageObject.getString("content"),LocalDate.parse(messageObject.getString("timestamp")));


            // Add the message to the list
            messages.add(message);
        }

        return messages;
    }

    public static void writeJsonFile(List<Message> messages) throws IOException, JSONException {
        // Create a JSONObject to hold the entire chat data
        JSONObject jsonObject = new JSONObject();

        // Convert the list of messages to a JSONArray
        JSONArray messagesArray = new JSONArray();
        for (Message message : messages) {
            try {
                JSONObject messageObject = new JSONObject();
                messageObject.put("sender_id", message.getSender());
                messageObject.put("receiver_id", message.getReciever());
                messageObject.put("content", message.getMessageContent());
                messageObject.put("timestamp", message.getTimeOfMessage().toString());
                messagesArray.put(messageObject);
            } catch (JSONException ex) {
                Logger.getLogger(chatFileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Add messages array to the root JSONObject
        jsonObject.put("messages", messagesArray);

        // Write the JSON object to the file
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonObject.toString(4)); 
        }
    }
}