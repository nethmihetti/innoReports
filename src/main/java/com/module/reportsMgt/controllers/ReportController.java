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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(path = ReportUrls.LocalUrls.REPORTS_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @RequestMapping(path = "/reports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getAllReports(HttpServletRequest request) {
        String method = request.getMethod();
        if ("T".equals(method) || "GET".equals(method)) {
            List<ReportModel> reports = reportService.getAll();
            return reports;
        }
        return null;
    }

    //http://localhost:8080/reports/all?user_id=1
//    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<ReportModel> getReportsByUser(@PathVariable("user_email") String email) {
//        List<ReportModel> reports = reportService.getAllByUserEmail(email);
//        return reports;
//    }

    //http://localhost:8080/reports/all?user_id=1
    @RequestMapping(path = ReportUrls.LocalUrls.USER_REPORTS_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReportModel> getReportsByUser(HttpServletRequest request, @RequestHeader String UserEmail) {
        String method = request.getMethod();
        if ("T".equals(method) || "GET".equals(method)) {
            List<ReportModel> reports = reportService.getAllByUserEmail(UserEmail);
            return reports;
        }
        return null;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ReportModel getReportById(@PathVariable("report_id") String reportId) {
        ReportModel report = reportService.getById(reportId);
        return report;
    }

    @RequestMapping(value = ReportUrls.LocalUrls.CREATE_REPORT_URL)
    public @ResponseBody
    String createReport(HttpServletRequest request,
                        @RequestHeader String Authorization,
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
        String method = request.getMethod();

        if ("ST".equals(method) || "POST".equals(method)) {

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
            reportForm.setStatus(ReportStatusEnum.RECEIVED);

            String imagePath = firebaseService.saveImage(FirebaseServiceIMPL.convert(image));
            reportForm.setImagePath(imagePath);

//        ReportForm reportForm = ReportForm.getReportForm(reportModel);

            String result = this.reportService.saveForm(reportForm);

            return result;
        }
        return null;
    }

    @RequestMapping(path = ReportUrls.LocalUrls.UPDATE_REPORT_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateReport(@PathVariable("report_id") String reportId, @PathVariable("new_status") ReportStatusEnum status){
        reportService.updateStatus(reportId, status);
    }
}
