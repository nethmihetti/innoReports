package com.module.reportsMgt.entities;

import com.module.reportsMgt.enums.ReportStatusEnum;
import com.module.reportsMgt.enums.ReportTagEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class ReportEntity {

    private String title;
    private String description;
    private String location;
    private String imagePath;
    private ReportStatusEnum status;
    private List<ReportTagEnum> tags;

    public ReportEntity(String title, String description) {
        this.title = title;
        this.description = description;
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
}
