package com.module.authorization.controller;

import com.module.authorization.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

  //entry point for api calls should go here


}
