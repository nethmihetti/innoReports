package com.module.reportsMgt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.module.reportsMgt.enums.ReportStatusEnum;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {
    private String rId;
    private String title;
    private String description;
    private String location;
    private String imagePath;
    private String date;
    private ReportStatusEnum status;
    private List<String> tags;
    @JsonIgnore
    private List<EntityModel> belongs;
    @JsonIgnore
    private UserModel submits;
//    private ReportTagEnumList tags;

    public ReportModel() {
//        this.tags = new ReportTagEnumList();
        this.tags = new ArrayList<>();
        this.belongs = new ArrayList<>();
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EntityModel> getBelongs() {
        return belongs;
    }

    public void setBelongs(List<EntityModel> belongs) {
        this.belongs = belongs;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public UserModel getSubmits() {
        return submits;
    }

    public void setSubmits(UserModel submits) {
        this.submits = submits;
    }
}
