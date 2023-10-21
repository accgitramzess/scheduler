package com.genericscheduler.differentimpl.flow.stage.context;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "of")
public class FlowStageContext {

    private final FlowStageContext next;

    private final FlowStageContext previous;

    // shared state
}
