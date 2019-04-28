package com.module.reportsMgt.service.intr;

import com.module.reportsMgt.models.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassificationService {
    void init();

    public List<EntityModel> getServices(List<String> tags);
}
