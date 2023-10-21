package com.genericscheduler.differentimpl.flow;

import java.util.Optional;

public enum FlowId {

    SYNC_ALLOCATION_DETAILS_EVENT,

    SYNC_CLOUD_EVENT,

    SYNC_CHECK_EVENT;

    public static Optional<FlowId> flowIdFromString(String flowIdName) {
        FlowId flowId = FlowId.valueOf(flowIdName);

        return Optional.ofNullable(flowId);
    }
}
