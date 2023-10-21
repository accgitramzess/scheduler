package com.genericscheduler.differentimpl.source.handler;

import com.genericscheduler.differentimpl.source.event.SourceEvent;

public interface SourceEventHandler {

    void run(SourceEvent<?> sourceEvent);
}
