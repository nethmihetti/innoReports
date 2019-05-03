package com.module.reportsMgt.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class EntityModel {
    private String name;
    private String email;
    private String address;
    private String[] tags;
    @JsonProperty("eId")
    private String eid;

    public EntityModel(String name, String email, String address, String[] tags, String eid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.tags = tags;
        this.eid = eid;
    }

    public EntityModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
