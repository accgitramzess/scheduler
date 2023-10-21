package com.genericscheduler.differentimpl.pipeline.executor.dag.task;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTask implements Task {

    protected final String taskName;

    @Override
    public String getName() {
        return taskName;
    }

    @Override
    public void run() {
        runTask();
    }

    abstract protected void runTask();
}
