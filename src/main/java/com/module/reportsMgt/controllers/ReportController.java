package com.module.reportsMgt.controllers;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.models.EntityModel;
import com.module.reportsMgt.models.ReportModel;
import com.module.reportsMgt.service.intr.ClassificationService;
import com.module.reportsMgt.service.intr.ReportService;
import com.module.reportsMgt.utils.FirebaseRepository;
import com.module.reportsMgt.utils.ReportChecker;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    ClassificationService classificationService;

    //entry point for api calls should go here

    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(path = "/reports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getAllReports() {
        List<ReportModel> reports = reportService.getAll();
        return reports;
    }

    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getReportsByUser(@RequestParam("user_email") String userEmail) {
        List<ReportModel> reports = reportService.getAllByUserEmail(userEmail);
        return reports;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportModel getReportById(@RequestParam("report_id") int reportId) {
        ReportModel report = reportService.getById(reportId);
        return report;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_IMAGE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveImage(String filename) throws IOException, NoSuchAlgorithmException {
        FirebaseRepository fr = new FirebaseRepository();
        fr.saveFile("Банан.PNG");
        System.out.println();
    }


    @RequestMapping(path = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportModel getEntityExample() {
        ReportModel reportModel = new ReportModel();
        reportModel.setTitle("Title");
        reportModel.setDescription("Description");
        reportModel.setDate("Date");
        reportModel.setLocation("Horizontal Vertical");
        reportModel.setStatus(ReportStatusEnum.IN_PROGRESS);
        reportModel.setImagePath("Image Path");
        reportModel.setEntities(new ArrayList<>());
        reportModel.setImagePath("https://www.googleapis.com/download/storage/v1/b/innoreport-66483.appspot.com/o/%D0%91%D0%B0%D0%BD%D0%B0%D0%BD.PNG?generation=1555855353369369&alt=media");

        reportModel.getTags().add(ReportTagEnum.MEDICINE);
        reportModel.getTags().add(ReportTagEnum.ELECTRICITY);
        reportModel.getTags().add(ReportTagEnum.HEALTH);
//        reportModel.getTags().getTags().add(ReportTagEnum.MEDICINE);
//        reportModel.getTags().getTags().add(ReportTagEnum.ELECTRICITY);
        return reportModel;
    }

    @RequestMapping(value = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.POST)
    public @ResponseBody
//    ResponseEntity<ReportModel> createReport(@RequestParam String title, @RequestParam String description, @RequestParam String location, @RequestParam ReportTagEnumList tags) {
    ReportModel createReport(@RequestBody ReportModel reportModel) {
        ReportChecker.checkTitle(reportModel);
        ReportChecker.checkTitle(reportModel);
        List<EntityModel> entities = classificationService.getServices(reportModel.getTags());
        reportModel.setEntities(entities);
        reportModel.setStatus(ReportStatusEnum.IN_PROGRESS);
        ReportModel result = this.reportService.save(reportModel);
        return result;
    }

}
