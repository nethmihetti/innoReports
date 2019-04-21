package com.module.reportsMgt.models;

import java.util.ArrayList;
import java.util.List;

public class ReportModelList {
    private List<ReportModel> reports;

    public ReportModelList() {
        reports = new ArrayList<>();
    }

    public List<ReportModel> getReports() {
        return reports;
    }

    public void setReports(List<ReportModel> reports) {
        this.reports = reports;
    }
}
