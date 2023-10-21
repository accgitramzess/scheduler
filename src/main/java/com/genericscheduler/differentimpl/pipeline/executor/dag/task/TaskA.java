package com.genericscheduler.differentimpl.pipeline.executor.dag.task;

import java.util.Set;

public class TaskA extends AbstractTask {

    public TaskA(String taskName) {
        super(taskName);
    }

    @Override
    protected void runTask() {
        System.out.println("Run task, name = " + taskName);
    }

    @Override
    public Set<String> getDependencies() {
        return Set.of("TaskC");
    }
}
