package com.module.reportsMgt.controllers;

import com.module.reportsMgt.entities.ReportEntity;
import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.service.ReportService;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(ReportUrls.LocalUrls.REPORTS_URL)
public class ReportController {

    @Autowired
    ReportService reportService;

    //entry point for api calls should go here

    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportEntity> getAllReports() {
        List<ReportEntity> reports = reportService.getAll();
        return reports;
    }

    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportEntity> getReportsByUser(@RequestParam("user_email") String userEmail) {
        List<ReportEntity> reports = reportService.getAllByUserEmail(userEmail);
        return reports;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportEntity getReportById(@RequestParam("report_id") int reportId) {
        ReportEntity report = reportService.getById(reportId);
        return report;
    }



    @RequestMapping(path = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportEntity getEntityExample() {
        ReportEntity reportEntity = new ReportEntity("Title", "Description");
        reportEntity.setLocation("Horizontal Vertical");
        reportEntity.setStatus(ReportStatusEnum.IN_PROGRESS);
        List<ReportTagEnum> tags = new ArrayList<>();
        reportEntity.setImagePath("Image Path");
        tags.add(ReportTagEnum.MEDICINE);
        tags.add(ReportTagEnum.ELECTRICITY);
        reportEntity.setTags(tags);
        return reportEntity;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_POST_URL, method = RequestMethod.POST)
    public @ResponseBody
    ReportEntity createReport(String title, String description, String location) {
        ReportEntity reportEntity = new ReportEntity(title, description);
        reportEntity.setLocation(location);
        //IMPLEMENT FIREBASE SAVING!
//        reportEntity.setImagePath(file.getName());
        reportEntity.setStatus(ReportStatusEnum.IN_PROGRESS);
        //QUESTINABLE ABOUT ENUMS
//        reportEntity.setTags(tags);
        ReportEntity result = this.reportService.save(reportEntity);
        return result;
    }


}
