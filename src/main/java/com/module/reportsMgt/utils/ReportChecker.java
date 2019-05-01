package com.module.reportsMgt.utils;

import com.module.reportsMgt.forms.ReportForm;

public class ReportChecker {

    static final int TITLE_LENGTH = 50;
    static final int DESC_LENGTH = 600;

    public static void checkTitle(ReportForm reportForm) {
        if (reportForm.getTitle().length() > TITLE_LENGTH) {
            String newTitle = reportForm.getTitle().substring(0, TITLE_LENGTH);
            reportForm.setTitle(newTitle);
        }
    }

    public static void checkDescription(ReportForm reportForm) {
        if (reportForm.getDescription().length() > DESC_LENGTH) {
            String newDesc = reportForm.getDescription().substring(0, DESC_LENGTH);
            reportForm.setDescription(newDesc);
        }
    }
}
