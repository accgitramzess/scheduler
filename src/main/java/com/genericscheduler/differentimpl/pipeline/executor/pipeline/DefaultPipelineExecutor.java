package com.genericscheduler.differentimpl.pipeline.executor.pipeline;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import org.springframework.util.concurrent.ListenableFutureTask;

import com.genericscheduler.differentimpl.pipeline.executor.PipelineExecutor;
import com.genericscheduler.differentimpl.pipeline.pip.Pipeline;
import com.genericscheduler.differentimpl.pipeline.pip.PipelineType;
import com.genericscheduler.differentimpl.pipeline.registry.PipelineRegistry;
import com.genericscheduler.differentimpl.pipeline.pip.PipelineLifeCycleEventContext;

public class DefaultPipelineExecutor implements PipelineExecutor {

    private PipelineRegistry pipelineRegistry;

    private final Executor threadPoolExecutor = Executors.newFixedThreadPool(4);

    private final Map<Pipeline, ListenableFutureTask<SequentialPipelineRunner>> runners;

    public DefaultPipelineExecutor() {
        runners = new HashMap<>();
    }

    @Override
    public void schedulePipeline(Pipeline pipeline) {
        PipelineType pipelineType = pipeline.getPipelineType();
        switch (pipelineType) {
            case SEQUENTIAL:
                scheduleSequentialPipeline(pipeline);
                break;
            case DAG:
                break;
            default:
                throw new RuntimeException("PipelineType is not supported!!!");
        }
    }

    private void scheduleSequentialPipeline(Pipeline pipeline) {
        SequentialPipelineRunner sequentialPipelineRunner = new SequentialPipelineRunner(pipeline);
        ListenableFutureTask<SequentialPipelineRunner> listenableFutureTask =
                new ListenableFutureTask<>(sequentialPipelineRunner, null);
        listenableFutureTask.addCallback(this);

        threadPoolExecutor.execute(listenableFutureTask);
    }

    @Override
    public void unSchedulePipeline(Supplier<?> pipelineIdSupplier) {

    }

    @Override
    public void onSuccess(Pipeline pipeline) {
        System.out.println("Pipeline finished : " + pipeline);
        pipelineRegistry.onPipelineFinishedEvent(new PipelineLifeCycleEventContext());
    }

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Pipeline finished : " + ex.getMessage());
        pipelineRegistry.onPipelineFinishedEvent(null);
    }
}
