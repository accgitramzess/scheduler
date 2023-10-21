package com.genericscheduler.differentimpl.source.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SourceEvent<SOURCE_EVENT_PAYLOAD> {

    private final SourceEventType sourceEventType;

    private final SOURCE_EVENT_PAYLOAD payload;
}
