package com.genericscheduler.differentimpl.flow.factory;

import java.util.List;

import com.genericscheduler.differentimpl.flow.Flow;
import com.genericscheduler.differentimpl.flow.stage.FlowStage;

public interface FlowFactory {

    Flow createFlow(String flowId);
    Flow createFlow(String flowId, List<FlowStage> flowStages);
}
