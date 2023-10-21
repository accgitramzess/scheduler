package com.genericscheduler.differentimpl.pipeline.handler;

public interface PipelineHandler {

    void execute(AbstractPipelineHandlerContext<?> aphctx);
}
