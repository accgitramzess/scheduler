package com.genericscheduler.differentimpl.flow.stage;

import java.util.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import com.genericscheduler.differentimpl.flow.action.FlowStageAction;
import com.genericscheduler.differentimpl.flow.stage.context.FlowStageContext;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class FlowStage implements Runnable, Iterable<FlowStageAction> {

    private final String flowStageId;

    private final List<FlowStageAction> flowStageActions = new ArrayList<>();

    private FlowStageStatus status;

    //private final FlowStageContext head;

    //private final FlowStageContext tail;

    @Override
    public Iterator<FlowStageAction> iterator() {

        return new Iterator<FlowStageAction>() {

            private int currentElement;

            @Override
            public boolean hasNext() {
                return currentElement < flowStageActions.size();
            }

            @Override
            public FlowStageAction next() {
                if(!hasNext())
                    throw new NoSuchElementException();

                return flowStageActions.get(currentElement ++);
            }
        };
    }

    public FlowStage action(FlowStageAction action) {
        action.setFlowStage(this);
        flowStageActions.add(action);

        return this;
    }

    public boolean isFinishedSuccessfully() {
        return Objects.isNull(status);
    }

    @Override
    public void run() {

    }
}
