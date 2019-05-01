package com.module.reportsMgt.utils;

public class ReportUrls {
    public interface LocalUrls{
        String BASE_URL = "http://localhost:8080";

        String REPORTS_URL = "/reports";

        String CREATE_REPORT_URL = REPORTS_URL + "/create";

        String REPORT_URL = REPORTS_URL + "/getReport/{report_id}";

        String USER_REPORTS_URL = REPORTS_URL + "/userReports";

        String UPDATE_REPORT_URL = REPORTS_URL + "/updateReport/{report_id}/{new_status}";

    }
}
