package com.genericscheduler.differentimpl.source.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.flow.Flow;
import com.genericscheduler.differentimpl.flow.impl.FirstFlowStageAction;
import com.genericscheduler.differentimpl.flow.impl.SecondFlowStageAction;
import com.genericscheduler.differentimpl.flow.stage.FlowStage;
import com.genericscheduler.differentimpl.source.flow.SourceEventFlowFactory;

@Component
public class DefaultSourceEventFlowFactory implements SourceEventFlowFactory {

    @Override
    public Set<Flow> createFlow() {
        return
                Set.of(
                        Flow.of(
                                "test-flow",
                                createFlowStages()
                        )
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
