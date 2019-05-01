package com.module.reportsMgt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.reportsMgt.forms.ReportForm;
import com.module.reportsMgt.models.ReportModel;
import com.module.reportsMgt.service.intr.ReportService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class ReportServiceIMPL implements ReportService {
    private String BASE_URL = "http://10.90.138.222:5000";
    private String REPORTS_URL = BASE_URL + "/innoreports/report/getAllReports";
    private String USER_REPORTS_URL = BASE_URL + "/innoreports/report/getReportHistory";
    private String REPORT_URL = BASE_URL + "/innoreports/report/getReport";
    private String REPORT_POST_URL = BASE_URL + "/innoreports/report/createReport";

   /* @Autowired
    AuthorizationRepository authorizationRepository;*/

    @Override
    @PostConstruct
    public void init() {
        String env = System.getenv("PERSISTENCE_SERVICE_URL");
        if (env != null) {
            this.BASE_URL = env;
            this.REPORTS_URL = BASE_URL + "/innoreports/report/getAllReports";
            this.USER_REPORTS_URL = BASE_URL + "/innoreports/report/getReportHistory";
            this.REPORT_URL = BASE_URL + "/innoreports/report/getReport";
            this.REPORT_POST_URL = BASE_URL + "/innoreports/report/createReport";
        }
    }

    @Override
    public ReportModel save(ReportModel reportModel) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            ObjectMapper mapper = new ObjectMapper();
            String jsonReport = mapper.writeValueAsString(reportModel);

            RequestEntity<String> requestEntity = RequestEntity.post(new URL(REPORT_POST_URL).toURI()).contentType(MediaType.APPLICATION_JSON).body(jsonReport);
            ResponseEntity<ReportModel> result = restTemplate.exchange(requestEntity, ReportModel.class);
//            ReportModel result = restTemplate.postForObject(ReportUrls.ApiUrls.StorageUrls.REPORT_POST_URL, reportModel, ReportModel.class);
            return result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String saveForm(ReportForm reportForm) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            ObjectMapper mapper = new ObjectMapper();
            String jsonReport = mapper.writeValueAsString(reportForm);

            RequestEntity<String> requestEntity = RequestEntity.post(new URL(REPORT_POST_URL).toURI()).contentType(MediaType.APPLICATION_JSON).body(jsonReport);
            ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);
//            ReportModel result = restTemplate.postForObject(ReportUrls.ApiUrls.StorageUrls.REPORT_POST_URL, reportModel, ReportModel.class);
            return result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ReportModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();

//        ResponseEntity<List<ReportModel>> response = restTemplate.exchange(
//                ReportUrls.ApiUrls.StorageUrls.REPORTS_URL,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<ReportModel>>(){});

        ResponseEntity<String> response = restTemplate.exchange(
                REPORTS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });

        String json = response.getBody().replace("None", "'None'").replace("'", "\"");

        ObjectMapper mapper = new ObjectMapper();

        List<ReportModel> list = null;
        try {
            list = mapper.readValue(json, new TypeReference<List<ReportModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<ReportModel> getAllByUserEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("user_email", email);


        ResponseEntity<String> response = restTemplate.exchange(
                USER_REPORTS_URL + "?email=" + email,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                },
                params);

        String json = response.getBody().replace("None", "'None'").replace("'", "\"");

        ObjectMapper mapper = new ObjectMapper();

        List<ReportModel> list = null;
        try {
            list = mapper.readValue(json, new TypeReference<List<ReportModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ReportModel getById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");

        ResponseEntity<String> response = restTemplate.exchange(
                REPORT_URL + "?id=" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                },
                params);

        String json = response.getBody().replace("None", "'None'").replace("'", "\"");


        ObjectMapper mapper = new ObjectMapper();

        ReportModel reportModel = null;
        try {
            reportModel = mapper.readValue(json, new TypeReference<ReportModel>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reportModel;
    }

    @Override
    public List<ReportModel> getAllByUser(String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        ResponseEntity<List<ReportModel>> response = restTemplate.exchange(
                USER_REPORTS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReportModel>>() {
                },
                params);
        return response.getBody();
    }
}
