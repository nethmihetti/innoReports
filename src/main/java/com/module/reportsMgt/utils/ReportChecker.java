package com.module.reportsMgt.utils;

import com.module.reportsMgt.models.ReportModel;

public class ReportChecker {

    static final int TITLE_LENGTH = 50;
    static final int DESC_LENGTH = 500;

    public static void checkTitle(ReportModel reportModel) {
        if (reportModel.getTitle().length() > TITLE_LENGTH) {
            String newTitle = reportModel.getTitle().substring(0, TITLE_LENGTH);
            reportModel.setTitle(newTitle);
        }
    }

    public static void checkDescription(ReportModel reportModel) {
        if (reportModel.getDescription().length() > DESC_LENGTH) {
            String newDesc = reportModel.getDescription().substring(0, DESC_LENGTH);
            reportModel.setDescription(newDesc);
        }
    }
}
