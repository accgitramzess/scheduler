package com.genericscheduler.differentimpl.pipeline.executor.dag.task;

import java.util.Set;

public class TaskD extends AbstractTask {

    public TaskD(String taskName) {
        super(taskName);
    }

    @Override
    protected void runTask() {
        System.out.println("Run task, name = " + taskName);
    }

    @Override
    public Set<String> getDependencies() {
        return Set.of("TaskE");
    }
}
