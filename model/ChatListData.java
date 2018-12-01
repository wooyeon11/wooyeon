package pyneer.full_time_wannabe.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class ChatListData implements Serializable {
    public Map<String, ChatListData> chatListData = new HashMap<>();

    private String chatName;
    private String chatID;
    private String users;
    private Object timeStamp;

    public ChatListData() {}
    public ChatListData(String chatName, String chatID, String users, Object timeStamp) {
        this.chatName = chatName;
        this.chatID = chatID;
        this.users = users;
        this.timeStamp = timeStamp;
    }

    public String getChatName() { return this.chatName; }
    public String getChatID() { return this.chatID; }
    public String getUsers() { return this.users; }
    public Object getTimeStamp() {return this.timeStamp; }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }
}
