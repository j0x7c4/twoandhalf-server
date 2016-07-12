package me.xiabb.twoandhalf.response;

/**
 * Created by jie on 16-7-12.
 */
public class SignupInfo extends BaseInfo{
    private String id;
    private String createdAt;
    private String sessionToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
