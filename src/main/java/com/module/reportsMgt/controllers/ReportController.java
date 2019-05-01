package com.module.reportsMgt.controllers;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.forms.ReportForm;
import com.module.reportsMgt.models.EntityModel;
import com.module.reportsMgt.models.ReportModel;
import com.module.reportsMgt.service.impl.FirebaseServiceIMPL;
import com.module.reportsMgt.service.intr.ClassificationService;
import com.module.reportsMgt.service.intr.FirebaseService;
import com.module.reportsMgt.service.intr.ReportService;
import com.module.reportsMgt.utils.ReportChecker;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.util.ArrayList;
import java.util.List;


@RestController
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 100)
@CrossOrigin(origins = "http://localhost:8080")
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    ClassificationService classificationService;

    @Autowired
    FirebaseService firebaseService;

    //entry point for api calls should go here
//    @PostConstruct
//    public void init() {
//        System.getenv().
//    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(path = "/reports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getAllReports() {
        List<ReportModel> reports = reportService.getAll();
        return reports;
    }

    //http://localhost:8080/reports/all?user_id=1
//    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<ReportModel> getReportsByUser(@PathVariable("user_email") String email) {
//        List<ReportModel> reports = reportService.getAllByUserEmail(email);
//        return reports;
//    }

    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getReportsByUser(@RequestHeader String UserEmail) {
        List<ReportModel> reports = reportService.getAllByUserEmail(UserEmail);
        return reports;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportModel getReportById(@PathVariable("report_id") String reportId) {
        ReportModel report = reportService.getById(reportId);
        return report;
    }

//    @RequestMapping(path = "/example", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getEntityExample() {
//        ReportModel reportModel = new ReportModel();
//        UserModel userModel = new UserModel();
//        userModel.setEmail("test@innopolis.ru");
//        reportModel.setSubmits(userModel);
//        reportModel.setTitle("Title");
//        reportModel.setDescription("Description");
//        reportModel.setDate("Date");
//        reportModel.setLocation("Horizontal Vertical");
//        reportModel.setStatus(ReportStatusEnum.IN_PROGRESS);
//        reportModel.setImagePath("Image Path");
//        reportModel.setImagePath("https://www.googleapis.com/download/storage/v1/b/innoreport-66483.appspot.com/o/uQV0aHOUKItor.jpeg?generation=1556032025406165&alt=media");
//        reportModel.getTags().add("MEDICINE");
//        reportModel.getTags().add("ELECTRICITY");
//        reportModel.getTags().add("HEALTH");
//
//        List<EntityModel> entities = classificationService.getServices(reportModel.getTags());
//        reportModel.setBelongs(entities);
//
//        ReportForm reportForm = ReportForm.getReportForm(reportModel);
//
//        String result = this.reportService.saveForm(reportForm);
//
////        reportModel.getTags().getTags().add(ReportTagEnum.MEDICINE);
////        reportModel.getTags().getTags().add(ReportTagEnum.ELECTRICITY);
//        return result;
//    }

    @RequestMapping(value = ReportUrls.LocalUrls.CREATE_REPORT_URL, method = RequestMethod.POST)
    public @ResponseBody
    String createReport(@RequestHeader String Authorization,
                        @RequestHeader String UserEmail,
                        @RequestParam("title") String title,
                        @RequestParam("description") String description,
                        @RequestParam("date") String date,
                        @RequestParam("tags[]") List<String> tags,
                        @RequestParam("image") MultipartFile image) {

//        UserModel userModel = userService.getUser(Authorization);
//
//        if (userModel.getEmail() == null) {
//            //no such user
//            return null;
//        }

        ReportForm reportForm = new ReportForm();
        reportForm.setTitle(title);
        reportForm.setDescription(description);
        reportForm.setDate(date);
        reportForm.setSubmits(UserEmail);

        reportForm.setTags(tags);
        ReportChecker.checkTitle(reportForm);
        ReportChecker.checkDescription(reportForm);

        List<EntityModel> entities = classificationService.getServices(reportForm.getTags());

        List<String> list = new ArrayList<>();
        entities.forEach(entityModel -> {
            list.add(entityModel.getName());
        });

        reportForm.setBelongs(list);
        reportForm.setStatus(ReportStatusEnum.IN_PROGRESS);

        String imagePath = firebaseService.saveImage(FirebaseServiceIMPL.convert(image));
        reportForm.setImagePath(imagePath);

//        ReportForm reportForm = ReportForm.getReportForm(reportModel);

        String result = this.reportService.saveForm(reportForm);

        return result;
    }


}
