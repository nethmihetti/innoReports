package com.module.reportsMgt.models;

import java.util.ArrayList;
import java.util.List;

public class EntityModelList {

    private List<EntityModel> entities;

    public EntityModelList() {
        this.entities = new ArrayList<>();
    }

    public List<EntityModel> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityModel> entities) {
        this.entities = entities;
    }
}
