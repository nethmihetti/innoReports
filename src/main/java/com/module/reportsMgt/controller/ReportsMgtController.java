package com.module.reportsMgt.controller;

import com.module.reportsMgt.service.ReportsMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/reports")
public class ReportsMgtController {

    @Autowired
    ReportsMgtService reportsMgtService;

  //entry point for api calls should go here


    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(value = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getReportsByUser (@RequestParam("user_id") String userId) {

        return userId;
    }
}
