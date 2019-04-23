package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.models.ReportModel;
import com.module.reportsMgt.service.intr.ReportService;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class ReportServiceIMPL implements ReportService {

   /* @Autowired
    AuthorizationRepository authorizationRepository;*/

    @Override
    public ReportModel save(ReportModel reportModel) {
        RestTemplate restTemplate = new RestTemplate();
        ReportModel result = restTemplate.postForObject(ReportUrls.ApiUrls.StorageUrls.REPORT_POST_URL, reportModel, ReportModel.class);
        return result;
    }

    @Override
    public List<ReportModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ReportModel>> response = restTemplate.exchange(
                ReportUrls.ApiUrls.ClassificationUrls.TAG_URLS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReportModel>>(){});

        return response.getBody();
    }

    @Override
    public List<ReportModel> getAllByUserEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("user_email", email);
        ResponseEntity<List<ReportModel>> response = restTemplate.exchange(
                ReportUrls.ApiUrls.ClassificationUrls.TAG_URLS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReportModel>>(){},
                params);
        return response.getBody();
    }

    @Override
    public ReportModel getById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("report_id", id + "");
        ReportModel reportModel = restTemplate.getForObject(ReportUrls.ApiUrls.StorageUrls.REPORT_URL, ReportModel.class, params);
        return reportModel;
    }

    @Override
    public List<ReportTagEnum> getTagEnums(List<String> tags) {
        List<ReportTagEnum> result = new ArrayList<>();
        for (String tag : tags) {
            result.add(ReportTagEnum.valueOf(tag.toUpperCase()));
        }
        return result;
    }
}
