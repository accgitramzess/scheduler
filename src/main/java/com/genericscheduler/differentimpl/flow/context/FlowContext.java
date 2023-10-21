package com.genericscheduler.differentimpl.flow.context;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.genericscheduler.differentimpl.flow.stage.FlowStage;

@Getter
@RequiredArgsConstructor
public class FlowContext {

    private final String flowId;

    private final Map<String, FlowStage> flowStages;

    public FlowStage getFlowStageById (String flowStageId) {
        return flowStages.get(flowStageId);
    }
}
