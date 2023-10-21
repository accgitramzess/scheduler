package com.genericscheduler.differentimpl.pipeline.executor;

import java.util.function.Supplier;

import org.springframework.util.concurrent.ListenableFutureCallback;

import com.genericscheduler.differentimpl.pipeline.pip.Pipeline;

public interface PipelineExecutor extends ListenableFutureCallback<Pipeline> {

    void schedulePipeline(Pipeline pipeline);

    void unSchedulePipeline(Supplier<?> pipelineIdSupplier);
}
