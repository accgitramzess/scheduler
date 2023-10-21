package com.genericscheduler.differentimpl.source;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import com.genericscheduler.differentimpl.source.event.SourceEventType;
import com.genericscheduler.differentimpl.source.handler.SourceEventHandler;

@Component
@RequiredArgsConstructor
public class SourceRegistry {

    private final Map<SourceEventType, SourceEventHandler> sourceEventHandlers;
}
