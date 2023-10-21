package com.genericscheduler.differentimpl.pipeline.executor.dag.graph;

import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import com.genericscheduler.differentimpl.pipeline.executor.dag.task.Task;

public interface GraphBuilder {

    DirectedAcyclicGraph<Task, DefaultEdge> buildGraph(Map<String, Task> tasks);
}
