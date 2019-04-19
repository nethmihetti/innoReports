package com.module.reportsMgt.enums;

import java.util.ArrayList;
import java.util.List;

public class ReportTagEnumList {
    private List<ReportTagEnum> tags;

    public ReportTagEnumList() {
        this.tags = new ArrayList<>();
    }

    public List<ReportTagEnum> getTags() {
        return tags;
    }

    public void setTags(List<ReportTagEnum> tags) {
        this.tags = tags;
    }
}
