package com.genericscheduler.differentimpl.pipeline.executor.dag;

import com.genericscheduler.differentimpl.pipeline.executor.dag.task.Task;

public interface TaskExecutor {

    boolean addTask(Task task);

    void start();

    void waiteToComplete();
}
