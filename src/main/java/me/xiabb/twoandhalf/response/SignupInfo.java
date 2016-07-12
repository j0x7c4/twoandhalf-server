package me.xiabb.twoandhalf.response;

/**
 * Created by jie on 16-7-12.
 */
public class SignupInfo extends BaseInfo{
    private String id;
    private String email;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
