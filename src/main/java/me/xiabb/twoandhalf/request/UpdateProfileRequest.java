package me.xiabb.twoandhalf.request;

/**
 * Created by jie on 16-7-13.
 */
public class UpdateProfileRequest {
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
