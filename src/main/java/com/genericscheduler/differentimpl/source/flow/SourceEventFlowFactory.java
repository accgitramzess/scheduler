package com.genericscheduler.differentimpl.source.flow;

import java.util.Set;

import com.genericscheduler.differentimpl.flow.Flow;

public interface SourceEventFlowFactory {

    Set<Flow> createFlow();
}
