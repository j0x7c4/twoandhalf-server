package me.xiabb.twoandhalf.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import me.xiabb.twoandhalf.response.Profile;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by jie on 16-7-12.
 */
public class ProfileService {
    private static Logger logger = LoggerFactory.getLogger(ProfileService.class);

    private MongoCollection<Document> sessionCollection;

    private MongoCollection<Document> userCollection;

    public ProfileService(MongoDatabase mongoDatabase) {
        sessionCollection = mongoDatabase.getCollection("Sessions");
        userCollection = mongoDatabase.getCollection("Users");
    }

    public Profile getProfileBySessionToken(String sessionToken) {
        Profile profile = new Profile();
        Document user = userCollection.find(new Document("username", sessionToken)).first();
        if (user==null) {
            return null;
        }
        profile.setUsername(user.getString("username"));
        profile.setEmail(user.getString("email"));
        profile.setCreatedAt(user.getString("createdAt"));
        profile.setUpdatedAt(user.getString("updatedAt"));
        profile.setWeiboId(user.getString("weiboId"));
        profile.setId(user.getString("_id"));
        profile.setSessionToken(sessionToken);
        return profile;
    }

    public Profile updateProfileBySessionToken(String sessionToken, Map<String, Object> update) {
        Profile profile = new Profile();
        String currentTime = LocalDateTime.now().toString();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", sessionToken);
        BasicDBObject updateProfile = new BasicDBObject();
        updateProfile.append("email", update.get("email").toString()).append("updatedAt", currentTime);
        userCollection.updateOne(query, updateProfile, (new UpdateOptions()).upsert(true));
        return profile;
    }
}
