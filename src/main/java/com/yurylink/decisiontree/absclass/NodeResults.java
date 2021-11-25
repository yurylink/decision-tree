package com.yurylink.decisiontree.absclass;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.common.node.NodeResult;

public class NodeResults extends Node{

    public NodeResults(NodeResult nodeResult) {
        super(null, null, null, null);
        this.nodeResult = nodeResult;
    }

    private final NodeResult nodeResult;

    @Override
    public String evaluate(TreeContext context){
        return nodeResult.name();
    }

}
