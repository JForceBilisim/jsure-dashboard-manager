package com.jforce.jsure.dashboard.manager.service.impl;

import com.jforce.jsure.base.db.service.BaseDbServiceImpl;
import com.jforce.jsure.base.restservice.model.DtoEntityModel;
import com.jforce.jsure.dashboard.manager.db.model.Widget;
import com.jforce.jsure.dashboard.manager.repository.WidgetRepository;
import com.jforce.jsure.dashboard.manager.rest.model.DtoWidget;
import com.jforce.jsure.dashboard.manager.service.IWidgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WidgetServiceImpl extends BaseDbServiceImpl<WidgetRepository, Widget> implements IWidgetService {

    @Override
    public Class<?> getDTOClassForService() {
        return DtoWidget.class;
    }

    @Override
    public <D extends DtoEntityModel> D toDTO(Widget widget) {
        DtoWidget dtoWidget = super.toDTO(widget);
        return (D) dtoWidget;
    }
}
