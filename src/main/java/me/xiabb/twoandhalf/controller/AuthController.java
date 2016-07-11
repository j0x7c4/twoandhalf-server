package me.xiabb.twoandhalf.controller;

import me.xiabb.twoandhalf.request.LoginRequest;
import me.xiabb.twoandhalf.request.LogoutRequest;
import me.xiabb.twoandhalf.request.SignupRequest;
import me.xiabb.twoandhalf.request.WeiboLoginRequest;
import me.xiabb.twoandhalf.response.LoginInfo;
import me.xiabb.twoandhalf.response.LogoutInfo;
import me.xiabb.twoandhalf.response.SignupInfo;
import me.xiabb.twoandhalf.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie on 16-7-12.
 */
@RestController("authController")
@RequestMapping("/api")
public class AuthController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/account/login",method = RequestMethod.POST)
    public LoginInfo loginInfo (@RequestBody LoginRequest request) {
        LoginInfo loginInfo = authService.login(request.getUsername(), request.getPassword());
        return loginInfo;
    }

    @RequestMapping(value = "/account/weibo",method = RequestMethod.POST)
    public LoginInfo weiboLoginInfo(@RequestBody WeiboLoginRequest request) {
        LoginInfo loginInfo = authService.weiboLogin(request.getUserID());
        return loginInfo;
    }

    @RequestMapping(value = "/account/logout",method = RequestMethod.POST)
    public LogoutInfo logoutInfo (@RequestBody LogoutRequest request) {
        LogoutInfo logoutInfo = new LogoutInfo();
        return logoutInfo;
    }

    @RequestMapping(value = "/account/register", method = RequestMethod.POST)
    public SignupInfo signupInfo (@RequestBody SignupRequest request) {
        SignupInfo signupInfo = authService.signUp(request.getUsername(), request.getEmail(), request.getPassword());
        return signupInfo;
    }
}
