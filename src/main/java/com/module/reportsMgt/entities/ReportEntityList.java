package com.module.reportsMgt.entities;

import java.util.ArrayList;
import java.util.List;

public class ReportEntityList {
    private List<ReportEntity> reports;

    public ReportEntityList() {
        reports = new ArrayList<>();
    }

    public List<ReportEntity> getReports() {
        return reports;
    }

    public void setReports(List<ReportEntity> reports) {
        this.reports = reports;
    }
}
