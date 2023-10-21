package com.genericscheduler.differentimpl.flow.impl;

import com.genericscheduler.differentimpl.flow.stage.FlowStage;
import com.genericscheduler.differentimpl.flow.action.FlowStageAction;
public class FirstFlowStageAction extends FlowStageAction {

    @Override
    protected void run(FlowStage flowStage) {
        System.out.println("FirstFlowStageAction - > run()");
    }
}
