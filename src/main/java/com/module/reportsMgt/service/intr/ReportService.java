package com.module.reportsMgt.service.intr;


import com.module.reportsMgt.forms.ReportForm;
import com.module.reportsMgt.models.ReportModel;

import java.util.List;

public interface ReportService {

    void init();

    ReportModel save(ReportModel reportModel);

    String saveForm(ReportForm reportForm);

    List<ReportModel> getAll();

    List<ReportModel> getAllByUserEmail(String email);

    ReportModel getById(String id);


    List<ReportModel> getAllByUser(String token);
}
