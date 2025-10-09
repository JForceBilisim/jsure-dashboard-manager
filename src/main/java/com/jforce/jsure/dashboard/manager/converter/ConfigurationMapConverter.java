package com.jforce.jsure.dashboard.manager.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jforce.jsure.utils.JStringUtil;
import jakarta.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class ConfigurationMapConverter implements AttributeConverter<Map<String, String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, String> map) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return json;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        if (JStringUtil.hasText(dbData)) {
            try {
                return objectMapper.readValue(dbData, Map.class);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }
}
