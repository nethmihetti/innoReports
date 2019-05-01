package com.module.reportsMgt.forms;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.models.ReportModel;

import java.util.ArrayList;
import java.util.List;

public class ReportForm {
    private String title;
    private String description;
    private String location;
    private String imagePath;
    private String date;
    private ReportStatusEnum status;
    private List<String> tags;
    private String rId;
    private List<String> belongs;
    private String submits;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReportStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ReportStatusEnum status) {
        this.status = status;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getBelongs() {
        return belongs;
    }

    public void setBelongs(List<String> belongs) {
        this.belongs = belongs;
    }

    public String getSubmits() {
        return submits;
    }

    public void setSubmits(String submits) {
        this.submits = submits;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public static ReportForm getReportForm(ReportModel reportModel) {
        ReportForm reportForm = new ReportForm();
        reportForm.setTitle(reportModel.getTitle());
        reportForm.setDescription(reportModel.getDescription());
        reportForm.setDate(reportModel.getDate());
        reportForm.setLocation(reportModel.getLocation());
        reportForm.setImagePath(reportModel.getImagePath());
        reportForm.setTags(reportModel.getTags());
        reportForm.setStatus(reportModel.getStatus());
        if (reportModel.getSubmits() != null) {
            reportForm.setSubmits(reportModel.getSubmits().getEmail());
        }
        reportForm.setBelongs(new ArrayList<>());
        reportModel.getBelongs().forEach(entityModel -> {
            reportForm.getBelongs().add(entityModel.getName());
        });

        return reportForm;
    }
}
