package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.models.EntityModel;
import com.module.reportsMgt.service.intr.ClassificationService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationServiceIMPL implements ClassificationService {
    String BASE_URL = "http://10.90.138.222:8081";
    String TAG_URLS = BASE_URL + "/classification/classify/{tags}";

    @Override
    public void init() {
        String env = System.getenv("ENTITY_PERSISTENCE_SERVICE_URL");
        if (env != null) {
            this.BASE_URL = env;
            this.TAG_URLS = BASE_URL + "/classification/classify/{tags}";
        }
    }

    @Override
    public List<EntityModel> getServices(List<String> tags) {
        RestTemplate restTemplate = new RestTemplate();

        String tagsS = String.join(",", tags);

        Map<String, String> params = new HashMap<>();
        params.put("tags", tagsS);

        ResponseEntity<List<EntityModel>> response = restTemplate.exchange(
                TAG_URLS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EntityModel>>(){},
                params);

        List<EntityModel> entities = response.getBody();

        return entities;
    }

}
