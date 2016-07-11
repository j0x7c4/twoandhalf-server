package me.xiabb.twoandhalf.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.xiabb.twoandhalf.response.Heartbeat;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("heartbeatController")
public class HeartbeatController {
    private static Logger logger = LoggerFactory.getLogger(HeartbeatController.class);

    @RequestMapping("/heartbeat")
    public Heartbeat heartbeat() {
        logger.info("heartbeat");
        return new Heartbeat();
    }
}
