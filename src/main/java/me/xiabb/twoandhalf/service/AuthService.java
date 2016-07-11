package me.xiabb.twoandhalf.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.xiabb.twoandhalf.response.LoginInfo;
import me.xiabb.twoandhalf.response.SignupInfo;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by jie on 16-7-12.
 */
@Service
public class AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private MongoCollection<Document> collection;

    public AuthService(MongoDatabase mongoDatabase) {
        collection = mongoDatabase.getCollection("Users");
    }

    public SignupInfo signUp(String username, String email, String password) {
        SignupInfo signupInfo = new SignupInfo();
        return signupInfo;
    }

    public LoginInfo login(String username, String password) {
        LoginInfo loginInfo = new LoginInfo();
        return loginInfo;
    }

    public LoginInfo weiboLogin(String weiboId) {
        LoginInfo loginInfo = new LoginInfo();
        return loginInfo;
    }
}
