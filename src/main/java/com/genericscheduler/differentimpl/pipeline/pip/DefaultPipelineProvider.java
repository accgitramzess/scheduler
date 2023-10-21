package com.genericscheduler.differentimpl.pipeline.pip;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.source.event.SourceEvent;
import com.genericscheduler.differentimpl.source.event.SourceEventType;

@Component
@RequiredArgsConstructor
public class DefaultPipelineProvider implements PipelineProvider<SourceEvent<?>> {

    private final Map<SourceEventType, PipelineFactory> pipelinesFactories;

    public DefaultPipelineProvider(List<PipelineFactory> pipelineFactories) {
        this.pipelinesFactories = pipelineFactories
                .stream()
                .collect(Collectors.toMap(PipelineFactory::getSourceEventType, Function.identity()));
    }

    @Override
    public Optional<Pipeline> getPipeline(SourceEvent<?> sourceEvent) {
        return getPipeline(sourceEvent, getPipelineFactory(sourceEvent).get());
    }

    private Optional<Pipeline> getPipeline(SourceEvent<?> sourceEvent, PipelineFactory pipelineFactory) {
        Pipeline pipeline = pipelineFactory.pipeline();
        if (Objects.isNull(pipeline)) {
            return Optional.empty();
        }

        pipeline.setSourceEvent(sourceEvent);

        return Optional.of(pipeline);
    }

    private Optional<PipelineFactory> getPipelineFactory(SourceEvent<?> sourceEvent) {
        SourceEventType sourceEventType = sourceEvent.getSourceEventType();
        PipelineFactory pipelineFactory = pipelinesFactories.get(sourceEventType);
        if (Objects.isNull(pipelineFactory)) {
            throw new RuntimeException("Can not find specific PipelineFactory for source event = " + sourceEventType);
        }

        return Optional.of(pipelineFactory);
    }
}
