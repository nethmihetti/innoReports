package com.module.reportsMgt.service.intr;

import com.module.reportsMgt.models.UserModel;

public interface UserService {
    public UserModel getUser(String userToken);
}
