package com.genericscheduler.differentimpl.source.handler;

import java.util.Map;
import java.util.Objects;

import com.genericscheduler.differentimpl.pipeline.executor.PipelineExecutor;
import lombok.RequiredArgsConstructor;

import com.genericscheduler.differentimpl.source.impl.ManualSourceEvent;
import com.genericscheduler.differentimpl.source.event.SourceEvent;
import com.genericscheduler.differentimpl.source.flow.SourceEventFlowFactory;
import com.genericscheduler.differentimpl.source.event.SourceEventType;

@RequiredArgsConstructor
public class GenericSinkSourceEventHandler implements SourceEventHandler {

    private final PipelineExecutor sourceEventFlowExecutor;

    private final Map<SourceEventType, SourceEventFlowFactory> sourceEventFlowFactories;

    public void run(SourceEvent<?> sourceEvent) {
        if (Objects.isNull(sourceEvent.getPayload())) {
            // proceed without payload, it means generic
        } else {
            switch (sourceEvent.getSourceEventType()) {
                case SCHEDULER:
                   // CronTaskEvent cronTaskEvent = (CronTaskEvent) sourceEvent.getPayload();
                   // SourceEventFlowFactory sourceEventFlowFactory = sourceEventFlowFactories.get(sourceEvent.getSourceEventType());
                   // Set<Flow> sourceEventToFlows = sourceEventFlowFactory.createFlow();
                    // schedule flows
                    break;
                case MANUALLY_API:
                    ManualSourceEvent manualSourceEvent = (ManualSourceEvent) sourceEvent.getPayload();
                    break;
                case KAFKA_EVENT:
                    // kafka support in feature
                    break;
            }

            //if (sourceEvent.getPayload() instanceof CronTaskEvent) {
                //CronTaskEvent cronTaskEvent = (CronTaskEvent) sourceEvent.getPayload();
                //SourceEventFlowFactory sourceEventFlowFactory = sourceEventFlowFactories.get(sourceEvent.getSourceEventType());
               // Set<Flow> flows = sourceEventFlowFactory.createFlow();
           // } else if (sourceEvent.getPayload() instanceof ManualSourceEvent) {
                ManualSourceEvent manualSourceEvent = (ManualSourceEvent) sourceEvent.getPayload();
            //}
        }
    }
}
