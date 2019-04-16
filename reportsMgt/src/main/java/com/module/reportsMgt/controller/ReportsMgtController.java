package com.module.reportsMgt.controller;

import com.module.reportsMgt.service.ReportsMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/authorization")
public class ReportsMgtController {

    @Autowired
    ReportsMgtService reportsMgtService;

  //entry point for api calls should go here


}
