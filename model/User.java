package pyneer.full_time_wannabe.model;

/**
 * Default User(part_time) Model
 */

public class User {
    private int id;             // DB key value
    private String email;       // user ID(email form)
    private String pw;          // user password
    private String name;


    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPw() { return pw; }
    public String getName() { return name; }


    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) { this.email = email; }
    public void setPw(String pw) { this.pw = pw; }
    public void setName(String name) { this.name = name; }
}
