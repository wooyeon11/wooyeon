package pyneer.full_time_wannabe.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ddjdd on 2018-11-04.
 */

public class ChatData {
    public Map<String, ChatData> chatData = new HashMap<>();

    private String userName;
    private String userID;
    private String chat;
    private Object timeStamp;
    public int type;

    public ChatData() {}
    public ChatData(String userName, String userID, String chat, Object timeStamp) {
        this.userName = userName;
        this.userID = userID;
        this.chat = chat;
        this.timeStamp = timeStamp;
    }

    public String getUserName() { return this.userName; }
    public String getUserID() { return this.userID; }
    public String getChat() { return this.chat; }
    public Object getTimeStamp() { return this.timeStamp; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setChat(String chat) { this.chat = chat; }
    public void setType(int type) { this.type = type; }

}
