package com.genericscheduler.differentimpl.source.scheduler.context;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import com.genericscheduler.differentimpl.flow.context.FlowContext;

@RequiredArgsConstructor
public class GenericSchedulerContext {

    private final Map<String, FlowContext> flowsContexts;

    public void addFlowContextByEvent(String event, FlowContext flowContext) {
        flowsContexts.put(event, flowContext);
    }

    public FlowContext getFlowContextByEvent(String event) {
        return flowsContexts.get(event);
    }
}
