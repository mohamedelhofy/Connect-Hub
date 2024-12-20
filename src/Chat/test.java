package Chat;
import connect.hub.User;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class  test {
    public static void main(String[] args) {
        ChatBackend chatBackend = new ChatBackend();

        // Create some test users
        User user1 = new User("", "alice@example.com", "Alice", "hashedPassword1", new Date(), true);
        User user2 = new User("", "bob@example.com", "Bob", "hashedPassword2", new Date(), true);
        chatBackend.users.add(user1);
        chatBackend.users.add(user2);
//        // Send a test message
        chatBackend.sendMessage(user1.getUserId(), user2.getUserId(), "ahahaha");

        chatBackend.sendMessage(user2.getUserId(), user1.getUserId(), "hahahaha");        
        // Write to JSON file


        // Read from JSON file
//        List<Message> jsonObject = chatBackend.getMessages(user1.getUserId(), user2.getUserId());
//        for (Message message :jsonObject)
//            System.out.println(message+"\n" );
    }
}