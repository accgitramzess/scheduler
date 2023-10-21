package com.genericscheduler.differentimpl.pipeline.handler;

import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Setter
@RequiredArgsConstructor
public abstract class AbstractPipelineHandler implements Runnable, PipelineHandler {

    private AbstractPipelineHandlerContext<?> prevContext;

    private AbstractPipelineHandlerContext<?> nextContext;

    @Override
    public void run() {
        try {
            execute(prevContext);
        } catch (Exception exception) {
            //flowStage.setStatus(exception);
        }
    }
}
