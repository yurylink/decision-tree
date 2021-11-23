package com.yurylink.decisiontree.absclass.impl;

import com.yurylink.decisiontree.common.model.*;
import com.yurylink.decisiontree.absclass.Node;
import com.yurylink.decisiontree.common.node.NodeResult;

public class ClientStatusActiveNode extends Node {

    @Override
    protected String getNodeDescription() {
        return "Evaluate if the Client is Active";
    }

    @Override
    protected boolean condition(TreeContext content) {
        return "ACTIVE".equals(content.getStatus().getStatus());
    }

    @Override
    protected String positiveOutcome(TreeContext content) {
        return "FINISH HERE";
    }

    @Override
    protected String negativeOutcome(TreeContext content) {
        return NodeResult.ERROR_INACTIVE_CLIENT.name();
    }
}
