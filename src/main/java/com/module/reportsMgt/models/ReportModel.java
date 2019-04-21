package com.module.reportsMgt.models;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {

    private String title;
    private String description;
    private String location;
    private String imagePath;
    private String date;
    private ReportStatusEnum status;
    private List<ReportTagEnum> tags;
    private List<EntityModel> entities;
//    private ReportTagEnumList tags;

    public ReportModel() {
//        this.tags = new ReportTagEnumList();
        this.tags = new ArrayList<>();
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

    public List<ReportTagEnum> getTags() {
        return tags;
    }

    public void setTags(List<ReportTagEnum> tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<EntityModel> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityModel> entities) {
        this.entities = entities;
    }
}
