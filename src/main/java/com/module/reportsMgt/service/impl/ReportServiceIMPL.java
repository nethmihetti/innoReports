package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.entities.ReportEntity;
import com.module.reportsMgt.entities.ReportEntityList;
import com.module.reportsMgt.service.ReportService;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class ReportServiceIMPL implements ReportService {

   /* @Autowired
    AuthorizationRepository authorizationRepository;*/

    @Override
    public int method() {
        return 1;
    }

    @Override
    public ReportEntity save(ReportEntity reportEntity) {
        RestTemplate restTemplate = new RestTemplate();
        ReportEntity result = restTemplate.postForObject(ReportUrls.ApiUruls.REPORT_POST_URL, reportEntity, ReportEntity.class);
        return result;
    }

    @Override
    public List<ReportEntity> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        ReportEntityList reportList = restTemplate.getForObject(ReportUrls.ApiUruls.REPORTS_URL, ReportEntityList.class);
        List<ReportEntity> reportEntities = reportList.getReports();
        return reportEntities;
    }

    @Override
    public List<ReportEntity> getAllByUserEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("user_email", email);
        ReportEntityList reportList = restTemplate.getForObject(ReportUrls.ApiUruls.REPORTS_URL, ReportEntityList.class, params);
        List<ReportEntity> reportEntities = reportList.getReports();
        return reportEntities;
    }

    @Override
    public ReportEntity getById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("report_id", id + "");
        ReportEntity reportEntity = restTemplate.getForObject(ReportUrls.ApiUruls.REPORT_URL, ReportEntity.class, params);
        return reportEntity;
    }
}
