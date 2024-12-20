import Chat.ChatBackend;
import Chat.Message;
import Chat.chatFileHandler;
import connect.hub.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;


public class chatFrontend extends JFrame implements Runnable {
    private ChatBackend chatBackend = new ChatBackend();
    private User currentUser = User.getInstance();
    private DefaultListModel<String> messageListModel;
    private JList<String> messageList;
    private JTextField messageInput;
    private volatile boolean running = true;
    private String receiver; 

    public chatFrontend(String receiver) {
        this.receiver = receiver;
        setTitle("Chat Window");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        messageListModel = new DefaultListModel<>();
        messageList = new JList<>(messageListModel);
        JScrollPane scrollPane = new JScrollPane(messageList);
        messageInput = new JTextField();
        JButton sendButton = new JButton("Send");
        
///////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Send  actiom////////////
        sendButton.addActionListener(e -> {
            String content = messageInput.getText();
            if (!content.isEmpty()) {
                chatBackend.sendMessage(currentUser.getUserId(), receiver, content);
                messageInput.setText(""); //because the input doesnor remain in text input 
                updateMessageList(); // qqq   Immediately show the sent message
            }
        });

///////////////////////// Input panel setup//////////////////////////

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(messageInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

 ////////////////////////////////// Load initial messagesظ///////////////
        updateMessageList();
        
        // Stop the thread when the window is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                running = false;
            }
        });

        // Start the thread//////////////////////////// 
        new Thread(this).start();
    }

    private void updateMessageList() {
        List<Message> messages = chatBackend.getMessages(currentUser.getUserId(), receiver);
        messageListModel.clear(); 
        for (Message message : messages) {
                        // Add all messages
            messageListModel.addElement(message.toString());
        }
        messageList.ensureIndexIsVisible(messageListModel.size() - 1); // for scrolling
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(2000); 
                updateMessageList(); //qqq  update mesages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
