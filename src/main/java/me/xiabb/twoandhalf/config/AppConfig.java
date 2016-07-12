package me.xiabb.twoandhalf.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import me.xiabb.twoandhalf.service.AuthService;
import me.xiabb.twoandhalf.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * Created by jie on 16-7-12.
 */
public class AppConfig {
    @Autowired
    private Environment env;

    @Autowired
    public MongoClient mongoClient;

    @Autowired
    public MongoDatabase mongoDatabase;

    @Bean
    public MongoClient mongoClient() {
        MongoClientURI connectionString = new MongoClientURI(env.getProperty("mongo.uri"));
        MongoClient mongoClient = new MongoClient(connectionString);
        return mongoClient;
    }

    @Bean
    public MongoDatabase mongoDatabase () {
        MongoDatabase database = mongoClient.getDatabase(env.getProperty("mongo.database"));
        return database;
    }

    @Bean
    public AuthService authService() {
        AuthService authService = new AuthService(mongoDatabase);
        return authService;
    }

    @Bean
    public ProfileService profileService() {
        ProfileService profileService = new ProfileService(mongoDatabase);
        return profileService;
    }
}
