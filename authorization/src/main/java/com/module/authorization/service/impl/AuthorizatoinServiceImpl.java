package com.module.authorization.service.impl;

import com.module.authorization.dao.AuthorizationRepository;
import com.module.authorization.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AuthorizationServiceImpl implements AuthorizationService {

   /* @Autowired
    AuthorizationRepository authorizationRepository;*/

    @Override
    public int method() {
        return 1;
    }

}
