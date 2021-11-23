package com.yurylink.decisiontree.absclass.impl;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.absclass.Node;
import com.yurylink.decisiontree.common.node.NodeResult;
import com.yurylink.decisiontree.absclass.NodeTypes;

public class ClientLegalAgeNode extends Node {

    @Override
    protected String getNodeDescription() {
        return "Evaluating if the Client is on legal age";
    }

    @Override
    protected boolean condition(TreeContext content) {
        return content.getClient().getAge() > 18;
    }

    @Override
    protected String positiveOutcome(TreeContext content) {
        return NodeTypes.ACTIVE_STATUS.evaluate(content);
    }

    @Override
    protected String negativeOutcome(TreeContext content) {
        return NodeResult.ERROR_INACTIVE_CLIENT.name();
    }

}
