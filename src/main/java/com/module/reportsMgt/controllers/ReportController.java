package com.module.reportsMgt.controllers;

import com.google.cloud.firestore.DocumentReference;
import com.module.reportsMgt.entities.ReportEntity;
import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.enums.ReportTagEnumList;
import com.module.reportsMgt.service.ReportService;
import com.module.reportsMgt.utils.FirebaseRepository;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    //entry point for api calls should go here

    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(path = "/reports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportEntity> getAllReports() {
        List<ReportEntity> reports = reportService.getAll();
        return reports;
    }

    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportEntity> getReportsByUser(@RequestParam("user_email") String userEmail) {
        List<ReportEntity> reports = reportService.getAllByUserEmail(userEmail);
        return reports;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportEntity getReportById(@RequestParam("report_id") int reportId) {
        ReportEntity report = reportService.getById(reportId);
        return report;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_IMAGE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveImage(String filename) throws IOException {
        FirebaseRepository fr = new FirebaseRepository();
        fr.saveFile(filename);
        System.out.println();
    }



    @RequestMapping(path = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportEntity getEntityExample() {
        ReportEntity reportEntity = new ReportEntity();
        reportEntity.setTitle("Title");
        reportEntity.setDescription("Description");
        reportEntity.setDate("Date");
        reportEntity.setLocation("Horizontal Vertical");
        reportEntity.setStatus(ReportStatusEnum.IN_PROGRESS);
        reportEntity.setImagePath("Image Path");

        reportEntity.getTags().add(ReportTagEnum.MEDICINE);
        reportEntity.getTags().add(ReportTagEnum.ELECTRICITY);
//        reportEntity.getTags().getTags().add(ReportTagEnum.MEDICINE);
//        reportEntity.getTags().getTags().add(ReportTagEnum.ELECTRICITY);
        return reportEntity;
    }

    @RequestMapping(value = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.POST)
    public @ResponseBody
//    ResponseEntity<ReportEntity> createReport(@RequestParam String title, @RequestParam String description, @RequestParam String location, @RequestParam ReportTagEnumList tags) {
    ReportEntity createReport(@RequestBody ReportEntity reportEntity ) {
//        ReportEntity reportEntity = new ReportEntity(title, description);
//        reportEntity.setLocation(location);
        //IMPLEMENT FIREBASE SAVING!
//        reportEntity.setImagePath(file.getName());
        reportEntity.setStatus(ReportStatusEnum.IN_PROGRESS);
        //QUESTINABLE ABOUT ENUMS
//        reportEntity.setTags(tags.getTags());
        ReportEntity result = this.reportService.save(reportEntity);
        return result;
    }


}
