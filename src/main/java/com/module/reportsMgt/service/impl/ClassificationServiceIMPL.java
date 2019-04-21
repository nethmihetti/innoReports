package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.enums.ReportTagEnum;
import com.module.reportsMgt.models.EntityModel;
import com.module.reportsMgt.models.EntityModelList;
import com.module.reportsMgt.service.intr.ClassificationService;
import com.module.reportsMgt.utils.ReportUrls;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClassificationServiceIMPL implements ClassificationService {
    @Override
    public List<EntityModel> getServices(List<ReportTagEnum> tags) {
        RestTemplate restTemplate = new RestTemplate();

        List<String> enumNames = tags.stream()
                .map(ReportTagEnum::name)
                .collect(Collectors.toList());

        String tagsS = String.join(",", enumNames);

        Map<String, String> params = new HashMap<>();
        params.put("tags", tagsS);

        ResponseEntity<List<EntityModel>> response = restTemplate.exchange(
                ReportUrls.ApiUruls.ClassificationUrls.TAG_URLS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EntityModel>>(){},
                params);

        List<EntityModel> entities = response.getBody();

        return entities;
    }

}
