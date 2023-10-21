package com.genericscheduler.differentimpl.pipeline.executor.dag.task;

import java.util.Set;

public interface Task extends Runnable {

    String getName();

    Set<String> getDependencies();
}
