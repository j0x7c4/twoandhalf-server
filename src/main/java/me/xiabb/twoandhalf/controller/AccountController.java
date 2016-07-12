package me.xiabb.twoandhalf.controller;

import com.google.common.collect.Maps;
import me.xiabb.twoandhalf.request.*;
import me.xiabb.twoandhalf.response.LoginInfo;
import me.xiabb.twoandhalf.response.LogoutInfo;
import me.xiabb.twoandhalf.response.Profile;
import me.xiabb.twoandhalf.response.SignupInfo;
import me.xiabb.twoandhalf.service.AuthService;
import me.xiabb.twoandhalf.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by jie on 16-7-12.
 */
@RestController("accountController")
@RequestMapping("/api/account")
public class AccountController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginInfo loginInfo (@RequestBody LoginRequest request) {
        LoginInfo loginInfo = authService.login(request.getUsername(), request.getPassword());
        return loginInfo;
    }

    @RequestMapping(value = "/weibo",method = RequestMethod.POST)
    public LoginInfo weiboLoginInfo(@RequestBody WeiboLoginRequest request) {
        LoginInfo loginInfo = authService.weiboLogin(request.getUserID());
        return loginInfo;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public LogoutInfo logoutInfo (@RequestBody LogoutRequest request) {
        LogoutInfo logoutInfo = new LogoutInfo();
        return logoutInfo;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public SignupInfo signupInfo (@RequestBody SignupRequest request) {
        SignupInfo signupInfo = authService.signUp(request.getUsername(), request.getEmail(), request.getPassword());
        return signupInfo;
    }

    @RequestMapping(value = "/profile/me",method = RequestMethod.GET)
    public Profile getProfile(@RequestHeader(value="Authorization", defaultValue="") String sessionToken) {
        Profile profile = profileService.getProfileBySessionToken(sessionToken);
        return profile;
    }

    @RequestMapping(value= "/profile/update", method = RequestMethod.POST)
    public Profile updateProfile(@RequestHeader(value = "Authorization", defaultValue = "") String sessionToken,
                                 @RequestBody UpdateProfileRequest request) {
        Map<String, Object> update = Maps.newHashMap();
        update.put("email", request.getEmail());
        Profile profile = profileService.updateProfileBySessionToken(sessionToken, update);
        return profile;
    }

}
