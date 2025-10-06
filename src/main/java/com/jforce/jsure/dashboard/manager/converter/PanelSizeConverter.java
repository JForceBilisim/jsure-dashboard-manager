package com.jforce.jsure.dashboard.manager.converter;

import com.jforce.jsure.dashboard.manager.enums.model.PanelSize;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PanelSizeConverter implements AttributeConverter<List<PanelSize>, String> {

    private static final String DELIM = ";";

    @Override
    public String convertToDatabaseColumn(List<PanelSize> panelSizes) {
        if (panelSizes == null || panelSizes.isEmpty()) return "";
        return panelSizes.stream()
                .map(lt -> lt == null ? "" : lt.name())
                .collect(Collectors.joining(DELIM));
    }

    @Override
    public List<PanelSize> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return new ArrayList<>();
        return Arrays.stream(dbData.split(DELIM, -1))
                .map(s -> s.isEmpty() ? null : PanelSize.valueOf(s))
                .collect(Collectors.toList());

    }
}
