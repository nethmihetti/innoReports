package com.module.reportsMgt.service;


import com.module.reportsMgt.entities.ReportEntity;

import java.util.List;

public interface ReportService {

    int method();

    public ReportEntity save(ReportEntity reportEntity);

    public List<ReportEntity> getAll();

    public List<ReportEntity> getAllByUserEmail(String email);

    public ReportEntity getById(int id);
}
