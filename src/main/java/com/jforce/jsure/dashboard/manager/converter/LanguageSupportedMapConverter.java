package com.jforce.jsure.dashboard.manager.converter;

import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.utils.JStringUtil;
import jakarta.persistence.AttributeConverter;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

public class LanguageSupportedMapConverter implements AttributeConverter<Map<LanguageType, String>, String> {


    private static final String SEPERATOR="#";
    private static final String KEY_VALUE_SEPERATOR="=";
    private static final String DEFAULT_VALUE_SEPERATOR="#";
    private static final String DEFAULT_KEY_VALUE_SEPERATOR="==";

    @Override
    public String convertToDatabaseColumn(Map<LanguageType, String> attributes) {
        return mapToString(attributes);
    }

    @Override
    public Map<LanguageType, String> convertToEntityAttribute(String dbData) {
        return stringToMap(dbData);
    }


    private String mapToString(Map<LanguageType, String> bundleAttrMap) {
        return toString(bundleAttrMap, SEPERATOR, KEY_VALUE_SEPERATOR, KEY_VALUE_SEPERATOR);
    }

    private Map<LanguageType, String> stringToMap(String value) {
        Map<LanguageType, String> map=new EnumMap<>(LanguageType.class);
        if(JStringUtil.isNullOrBlank(value)) {
            return map;
        }
        String [] mapEntries=value.split(SEPERATOR);
        if(mapEntries==null || mapEntries.length==0) {
            return map;
        }
        for (String entry : mapEntries) {
            String[] keyValueArray= entry.split(KEY_VALUE_SEPERATOR);
            if(keyValueArray==null || keyValueArray.length==2) {

                String mapKey=keyValueArray[0];
                String mapValue=keyValueArray[1];
                Optional<LanguageType> languageTypeOpt = Optional.ofNullable(LanguageType.getLanguageFromValue(mapKey));
                if(languageTypeOpt.isPresent() && JStringUtil.isValid(mapValue)) {
                    map.put(languageTypeOpt.get(), mapValue);
                }

            }

        }
        return map;
    }
    public <K extends Object,V extends Object> String toString(Map<K, V> map,String seperator,String keyValueSeperator,String defaultValue) {
        if(map==null || map.isEmpty()) {
            return defaultValue;
        }
        StringJoiner joiner=new StringJoiner(JStringUtil.getDefault(seperator, DEFAULT_VALUE_SEPERATOR));
        String keyValueSeperatorFinal=JStringUtil.getDefault(keyValueSeperator, DEFAULT_KEY_VALUE_SEPERATOR);
        map.forEach((k,v)->{
            StringBuilder buffer=new StringBuilder();
            buffer.append(k.toString()).append(keyValueSeperatorFinal).append(v.toString());
            joiner.add(buffer.toString());
        });
        return JStringUtil.isValid(joiner.toString())?joiner.toString():defaultValue;
    }

}
