package com.genericscheduler.differentimpl.flow.action;

import com.genericscheduler.differentimpl.flow.stage.FlowStage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
public abstract class FlowStageAction implements Runnable {

    private FlowStage flowStage;

    @Override
    public void run() {
        try {
            run(flowStage);
        } catch (Exception exception) {
            //flowStage.setStatus(exception);
        }
    }

    abstract protected void run(FlowStage flowStage);
}
