package pyneer.full_time_wannabe.model;

/**
 * Created by ddjdd on 2018-11-27.
 */

public class FriendData {
    private String userName;
    private int type;

    public FriendData() {}
    public FriendData(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() { return userName; }
    public int getType() {
        return type;
    }
}
