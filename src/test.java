import connect.hub.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class  test {
    public static void main(String[] args) {
        ChatBackend chatBackend = new ChatBackend();
        chatFileHandler fileHandler = new chatFileHandler();

        // Create some test users
        User user1 = new User(User.generateUniqueId(), "alice@example.com", "Alice", "hashedPassword1", new Date(), true);
        User user2 = new User(User.generateUniqueId(), "bob@example.com", "Bob", "hashedPassword2", new Date(), true);
        chatBackend.users.add(user1);
        chatBackend.users.add(user2);
        // Send a test message
        chatBackend.sendMessage(user1.getUserId(), user2.getUserId(), "Hello, Bob!");
        
        // Write to JSON file
        try {
            fileHandler.writeJsonFile(chatBackend.users, chatBackend.messages);
            System.out.println("Data written to file successfully.");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        // Read from JSON file
        try {
            JSONObject jsonObject = fileHandler.readJsonFile();
            System.out.println("Data read from file: " + jsonObject.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}