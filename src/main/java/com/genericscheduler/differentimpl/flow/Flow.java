package com.genericscheduler.differentimpl.flow;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import com.genericscheduler.differentimpl.flow.stage.FlowStage;

@Builder
@RequiredArgsConstructor(staticName = "of")
public class Flow implements Runnable, Iterable<FlowStage> {

    private final String flowId;

    private final List<FlowStage> flowStages;

    @Override
    public Iterator<FlowStage> iterator() {

        return new Iterator<FlowStage>() {

            private int currentElement;

            @Override
            public boolean hasNext() {
                return currentElement < flowStages.size();
            }

            @Override
            public FlowStage next() {
                if(!hasNext())
                    throw new NoSuchElementException();

                return flowStages.get(currentElement ++);
            }
        };
    }

    public Flow flowStage(FlowStage flowStage) {
        flowStages.add(flowStage);

        return this;
    }

    @Override
    public void run() {
    }
}
