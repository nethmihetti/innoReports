package com.module.reportsMgt.service.intr;


import com.module.reportsMgt.models.ReportModel;

import java.util.List;

public interface ReportService {

    int method();

    public ReportModel save(ReportModel reportModel);

    public List<ReportModel> getAll();

    public List<ReportModel> getAllByUserEmail(String email);

    public ReportModel getById(int id);
}
