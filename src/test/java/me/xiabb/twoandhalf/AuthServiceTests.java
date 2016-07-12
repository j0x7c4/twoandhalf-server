package me.xiabb.twoandhalf;

import me.xiabb.twoandhalf.config.DevAppConfig;
import me.xiabb.twoandhalf.response.LoginInfo;
import me.xiabb.twoandhalf.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jie on 16-7-12.
 */
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DevAppConfig.class)
public class AuthServiceTests {
    @Autowired
    private AuthService authService;
    @Test
    public void signUp() {
        String email = "abc@123.com";
        String username = "abc";
        String password = "abc";
        authService.signUp(username, email, password);
    }
    @Test
    public void checkUserExist() {
        if( authService.checkUserExist(null,"abc@123.com") ) {
            System.out.print("Yes");
        } else {
            System.out.print("No");
        }
    }
    @Test
    public void login() {
        LoginInfo loginInfo = authService.login("abc@1233.com","abc");
        System.out.print(loginInfo.getUsername()+" "+loginInfo.getEmail()+" "+loginInfo.getStatus()+ " " +loginInfo.getMessage());
    }

    @Test
    public void weiboLogin() {
        LoginInfo loginInfo = authService.weiboLogin("123456");
        System.out.print(loginInfo.getUsername()+" "+loginInfo.getEmail()+" "+loginInfo.getStatus()+ " " +loginInfo.getMessage());
    }
}
