package me.xiabb.twoandhalf.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.xiabb.twoandhalf.response.LoginInfo;
import me.xiabb.twoandhalf.response.SignupInfo;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mongodb.client.model.Filters;

import java.time.LocalDateTime;

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

    public boolean checkUserExist (String username, String email) {
        boolean flag;
        try {
            flag = collection.find(Filters.or(Filters.eq("username",username),Filters.eq("email",email))).first()!=null;
        } catch (Exception e) {
            logger.error("fail to find");
            flag = false;
        }
        return flag;
    }

    public SignupInfo signUp(String username, String email, String password) {
        SignupInfo signupInfo = new SignupInfo();
        try {
            if (!checkUserExist(username, email)) {
                String currentTime = LocalDateTime.now().toString();
                collection.insertOne(
                        new Document("_id", username)
                                .append("username", username)
                                .append("email", email)
                                .append("password", password)
                                .append("createdAt", currentTime)
                                .append("updatedAt", currentTime));
                signupInfo.setId(username);
                signupInfo.setStatus("ok");
                signupInfo.setSessionToken(username);
                signupInfo.setCreatedAt(currentTime);
            }
            else {
                signupInfo.setStatus("error");
                signupInfo.setMessage("ERROR_USER_ALREADY_EXIST");
            }
        } catch (Exception e) {
            logger.error("fail to new user", e.getMessage());
            signupInfo.setStatus("error");
            signupInfo.setMessage("ERROR_MONGO_DATABASE");
        }
        return signupInfo;
    }

    public LoginInfo login(String username, String password) {
        LoginInfo loginInfo = new LoginInfo();
        try {
            Document user = collection.find(
                    Filters.and(Filters.or(Filters.eq("username",username),Filters.eq("email",username)),
                            Filters.eq("password", password))).first();
            if (user==null) {
                loginInfo.setStatus("error");
                loginInfo.setMessage("ERROR_USER_NOT_FOUND");
            } else {
                loginInfo.setStatus("ok");
                loginInfo.setId(user.getString("_id"));
                loginInfo.setUsername(user.getString("username"));
                loginInfo.setEmail(user.getString("email"));
                loginInfo.setUpdatedAt(user.getString("updatedAt"));
                loginInfo.setCreatedAt(user.getString("createdAt"));
                loginInfo.setWeiboId(user.getString("weiboId"));
                loginInfo.setSessionToken(username);
            }
        } catch (Exception e) {
            logger.error("fail to find user", e.getMessage());
            loginInfo.setStatus("error");
            loginInfo.setMessage("ERROR_MONGO_DATABASE");
        }
        return loginInfo;
    }

    public LoginInfo weiboLogin(String weiboId) {
        LoginInfo loginInfo = new LoginInfo();
        Document user = null;
        try {
            user = collection.find(Filters.eq("weiboId",weiboId)).first();
        } catch (Exception e) {
            logger.error("fail to new user", e.getMessage());
            loginInfo.setStatus("error");
            loginInfo.setMessage("ERROR_MONGO_DATABASE");
        }
        if (user==null) {
            String currentTime = LocalDateTime.now().toString();
            try {
                String id = weiboId + ".weibo";
                String username = id;
                collection.insertOne(
                        new Document("_id", weiboId + ".weibo")
                                .append("username", weiboId + ".weibo")
                                .append("weiboId", weiboId)
                                .append("createdAt", currentTime)
                                .append("updatedAt", currentTime));
                loginInfo.setStatus("ok");
                loginInfo.setCreatedAt(currentTime);
                loginInfo.setUpdatedAt(currentTime);
                loginInfo.setMessage("INFO_COMPLETE_USER");
                loginInfo.setId(id);
                loginInfo.setUsername(username);
                loginInfo.setSessionToken(username);
                loginInfo.setWeiboId(weiboId);
            } catch (Exception e) {
                logger.error("fail to new user", e.getMessage());
                loginInfo.setStatus("error");
                loginInfo.setMessage("ERROR_MONGO_DATABASE");
            }
        } else {
            loginInfo.setId(user.getString("_id"));
            loginInfo.setUsername(user.getString("username"));
            loginInfo.setEmail(user.getString("email"));
            loginInfo.setWeiboId(user.getString("weiboId"));
            loginInfo.setStatus("ok");
            loginInfo.setSessionToken(user.getString("username"));
        }
        return loginInfo;
    }
}
