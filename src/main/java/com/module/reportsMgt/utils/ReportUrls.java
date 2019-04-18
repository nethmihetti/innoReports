package com.module.reportsMgt.utils;

public class ReportUrls {
    public interface LocalUrls{
        String BASE_URL = "http://localhost:8080";

        String REPORTS_URL = BASE_URL + "/reports";

        String REPORT_POST_URL = REPORTS_URL + "/submit";

        String REPORT_URL = REPORTS_URL + "/{report_id}";

    }

    public interface ApiUruls {
        String BASE_URL = "http://localhost:8080";

        String REPORTS_URL = BASE_URL + "/reports";

        String USER_REPORTS_URL = REPORTS_URL + "/{user_email}";

        String REPORT_URL = REPORTS_URL + "/{report_id}";

        String REPORT_POST_URL = REPORTS_URL + "/createreport";


    }

}
