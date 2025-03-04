package com.dailycodebuffer.springboot.tutorial.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featuresMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featuresMap.put("Department", new Feature(true));
        featuresMap.put("User", new Feature(true));
        featuresMap.put("Authentication", new Feature(true));
    }

    @ReadOperation
    public Map<String, Feature> features() {
        return featuresMap;
    }

    @ReadOperation
    public Feature getFeature(@Selector String name) {
        return featuresMap.get(name);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Feature {
        private boolean isEnabled;
    }
}
