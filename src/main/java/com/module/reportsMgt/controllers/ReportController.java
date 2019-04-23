package com.module.reportsMgt.controllers;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.models.EntityModel;
import com.module.reportsMgt.models.ReportModel;
import com.module.reportsMgt.service.impl.FirebaseServiceIMPL;
import com.module.reportsMgt.service.intr.ClassificationService;
import com.module.reportsMgt.service.intr.FirebaseService;
import com.module.reportsMgt.service.intr.ReportService;
import com.module.reportsMgt.service.intr.UserService;
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
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    ClassificationService classificationService;

    @Autowired
    UserService userService;

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

    @RequestMapping(value = ReportUrls.LocalUrls.CREATE_REPORT_URL, method = RequestMethod.POST)
    public @ResponseBody
    ReportModel createReport(@RequestHeader String Authorization,
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

        ReportModel reportModel = new ReportModel();
        reportModel.setTitle(title);
        reportModel.setDescription(description);
        reportModel.setDate(date);

        reportModel.setTags(reportService.getTagEnums(tags));
        ReportChecker.checkTitle(reportModel);
        ReportChecker.checkDescription(reportModel);

        List<EntityModel> entities = classificationService.getServices(reportModel.getTags());
        reportModel.setEntities(entities);
        reportModel.setStatus(ReportStatusEnum.IN_PROGRESS);

        String imagePath = firebaseService.saveImage(FirebaseServiceIMPL.convert(image));
        reportModel.setImagePath(imagePath);

        ReportModel result = this.reportService.save(reportModel);

        return result;
    }


}
