package com.genericscheduler.differentimpl.pipeline.executor.dag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.genericscheduler.differentimpl.pipeline.executor.dag.graph.GraphBuilder;
import com.genericscheduler.differentimpl.pipeline.executor.dag.graph.GraphBuilderImpl;
import com.genericscheduler.differentimpl.pipeline.executor.dag.task.*;
import lombok.extern.slf4j.Slf4j;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

@Slf4j
public class TaskExecutorImpl implements TaskExecutor {

    private final Map<String, Task> tasks;

    private final GraphBuilder graphBuilder;
    private DirectedAcyclicGraph<Task, DefaultEdge> graph;

    public TaskExecutorImpl() {
        this(new HashMap<>(), new GraphBuilderImpl());
    }

    public TaskExecutorImpl(Map<String, Task> tasks, GraphBuilder graphBuilder) {
        this.tasks = tasks;
        this.graphBuilder = graphBuilder;
    }

    @Override
    public boolean addTask(Task task) {
        log.info("Adding task: " + task.getName());
        if (tasks.put(task.getName(), task) != null) {
            throw new RuntimeException("Task with same name already exists : " + task.getName());
        }

        return true;
    }

    @Override
    public void start() {
        int cpus = Runtime.getRuntime().availableProcessors();
        //executor = new ThreadExecutor(cpus, 60, new LinkedBlockingQueue());
        graph = graphBuilder.buildGraph(tasks);
        int countOfTasks = this.graph.vertexSet().size();
        log.info("Starting... Num Tasks : " + countOfTasks);
        //startTime = System.currentTimeMillis();

        scheduleTasks();
    }

    private void scheduleTasks() {
        if (graph.vertexSet().size() == 0) {
            //executor.shutdown();
        }

       // synchronized (graph ) {
        TopologicalOrderIterator<Task, DefaultEdge> iter = new TopologicalOrderIterator<>(graph);
        Set executing = new HashSet();

        while(iter.hasNext()) {

            Task task = iter.next();
            //System.out.println(task.getName());

            Set<DefaultEdge> incomingEdges = graph.incomingEdgesOf(task);
            Set<DefaultEdge> outgoingEdges = graph.outgoingEdgesOf(task);
            Set<Task> ancestors = graph.getAncestors(task);

            if (incomingEdges.size() == 0 && !executing.contains(task)) {
                //executor.execute(task);
                //executing.add(task);
                task.run();
            }
        }
       // }

    }

    @Override
    public void waiteToComplete() {

    }

    public static void main(String[] args) {
        TaskExecutor taskExecutor = new TaskExecutorImpl();
        taskExecutor.addTask(new TaskA("TaskA"));
        taskExecutor.addTask(new TaskB("TaskB"));
        taskExecutor.addTask(new TaskC("TaskC"));
        taskExecutor.addTask(new TaskD("TaskD"));
        taskExecutor.addTask(new TaskE("TaskE"));
        taskExecutor.addTask(new TaskF("TaskF"));
        taskExecutor.start();
    }
}
