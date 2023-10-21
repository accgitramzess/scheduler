package com.genericscheduler.differentimpl.pipeline.executor.pipeline;

import com.genericscheduler.differentimpl.pipeline.handler.PipelineHandler;
import com.genericscheduler.differentimpl.pipeline.pip.Pipeline;

public class SequentialPipelineRunner implements Runnable {

    private final Pipeline pipeline;

    public SequentialPipelineRunner(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void run() {
        for (PipelineHandler pipelineHandler : pipeline.getPipelineHandlers()) {
            pipelineHandler.execute(null);
        }
    }
}
