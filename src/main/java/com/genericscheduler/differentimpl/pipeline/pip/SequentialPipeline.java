package com.genericscheduler.differentimpl.pipeline.pip;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.genericscheduler.differentimpl.pipeline.handler.PipelineHandler;

public class SequentialPipeline extends AbstractPipeline implements Iterable<PipelineHandler> {

    public SequentialPipeline(List<PipelineHandler> handlers) {
        super(handlers);
    }

    @Override
    public PipelineType getPipelineType() {
        return PipelineType.SEQUENTIAL;
    }

    @Override
    public Iterator<PipelineHandler> iterator() {

        return new Iterator<PipelineHandler>() {

            private int currentElement;

            @Override
            public boolean hasNext() {
                return currentElement < handlers.size();
            }

            @Override
            public PipelineHandler next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }

                return handlers.get(currentElement ++);
            }
        };
    }
}
