package com.genericscheduler.differentimpl.flow.factory;

import java.util.ArrayList;
import java.util.List;

import com.genericscheduler.differentimpl.flow.Flow;
import com.genericscheduler.differentimpl.flow.impl.FirstFlowStageAction;
import com.genericscheduler.differentimpl.flow.impl.SecondFlowStageAction;
import com.genericscheduler.differentimpl.flow.stage.FlowStage;
import com.genericscheduler.differentimpl.flow.stage.FlowStageType;

public class FlowsFactoryImpl implements FlowFactory {

    @Override
    public Flow createFlow(String flowId, List<FlowStage> flowStages) {
        return Flow.of(
                flowId,
                flowStages
        );
    }

    @Override
    public Flow createFlow(String flowId) {
        return Flow.of(
                flowId,
                createFlowStages()
        );
    }

    private List<FlowStage> createFlowStages() {
        List<FlowStage> flowStages = new ArrayList<>();

        FlowStage testFlowStage =
                FlowStage
                        .of("test-flow-stage")
                        .action(new FirstFlowStageAction())
                        .action(new SecondFlowStageAction());

        flowStages.add(testFlowStage);

        return flowStages;
    }
}
