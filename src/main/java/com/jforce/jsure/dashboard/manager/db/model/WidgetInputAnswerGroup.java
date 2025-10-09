package com.jforce.jsure.dashboard.manager.db.model;

import com.jforce.jsure.base.db.model.JsureDbEntity;
import com.jforce.jsure.base.enums.model.LanguageType;
import com.jforce.jsure.dashboard.manager.converter.LanguageSupportedMapConverter;
import com.jforce.jsure.dashboard.manager.enums.model.AnswerPossibleType;
import com.jforce.jsure.dashboard.manager.enums.model.SortType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
public class WidgetInputAnswerGroup extends JsureDbEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "group_code",unique = true,nullable = false)
    private String groupCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_possible_type", nullable = false)
    private AnswerPossibleType answerPossibleType;

    @Lob
    @Convert(converter = LanguageSupportedMapConverter.class)
    @Column(name = "name",nullable = false)
    private Map<LanguageType,String> name;

    @Enumerated(EnumType.STRING)
    @Column(name = "sort_type")
    private SortType sortType;
}
