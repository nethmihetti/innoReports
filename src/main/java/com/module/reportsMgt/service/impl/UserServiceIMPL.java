package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.models.UserModel;
import com.module.reportsMgt.service.intr.UserService;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceIMPL implements UserService {
    @Override
    public UserModel getUser(String userToken) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("token", userToken);
        UserModel userModel = restTemplate.getForObject(ReportUrls.ApiUrls.PersistanceUrls.USER_URL, UserModel.class, params);
        return userModel;
    }
}
