package com.genericscheduler.differentimpl.pipeline.handler;

import com.genericscheduler.differentimpl.source.event.SourceEvent;

public abstract class AbstractPipelineHandlerContext<PAYLOAD> {

    private SourceEvent<?> sourceEvent;

    private PAYLOAD payload;
}
