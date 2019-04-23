package com.module.reportsMgt.utils;

public class ReportUrls {
    public interface LocalUrls{
        String BASE_URL = "http://localhost:8080";

        String REPORTS_URL = "/reports";

        String CREATE_REPORT_URL = "/reports/create";

        String REPORT_IMAGE = REPORTS_URL + "/image";

        String REPORT_URL = REPORTS_URL + "/{report_id}";

        String USER_REPORTS_URL = REPORTS_URL + "/{user_email}";

    }

    public interface ApiUrls {
        interface StorageUrls {
            String BASE_URL = "http://localhost:8080";

            String REPORTS_URL = BASE_URL + "/reports";

            String USER_REPORTS_URL = REPORTS_URL + "/{user_email}";

            String REPORT_URL = REPORTS_URL + "/{report_id}";

            String REPORT_POST_URL = REPORTS_URL + "/createreport";
        }

        interface ClassificationUrls {
            String BASE_URL = "http://10.90.138.222:8081";

            String TAG_URLS = BASE_URL + "/classification/classify/{tags}";
        }

        interface PersistanceUrls {
            String BASE_URL = "http://10.90.138.222:8081";

            String USER_URL = BASE_URL + "/innoreports/user/getUser";
        }
    }


}
