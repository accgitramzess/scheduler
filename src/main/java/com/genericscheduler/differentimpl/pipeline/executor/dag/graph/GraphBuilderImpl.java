package com.genericscheduler.differentimpl.pipeline.executor.dag.graph;

import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import com.genericscheduler.differentimpl.pipeline.executor.dag.task.Task;

@Slf4j
public class GraphBuilderImpl implements GraphBuilder {

    @Override
    public DirectedAcyclicGraph<Task, DefaultEdge> buildGraph(Map<String, Task> tasks) {
        log.info("Building DAG of tasks");

        DirectedAcyclicGraph<Task, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        log.info("Adding tasks");

        for (Task task : tasks.values()) {
            graph.addVertex(task);
        }

        log.info("Adding Dependencies");

        for (Task task : tasks.values()) {

            Set<String> taskDependencies = task.getDependencies();

            for (String taskNameDependsOn : taskDependencies) {

                Task dependOnTask = tasks.get(taskNameDependsOn);

                log.info("Adding Dependency between " + task.getName() + " and " + dependOnTask.getName());
                graph.addEdge(dependOnTask, task);

            }
        }

        return graph;
    }
}
