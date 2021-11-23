package com.yurylink.decisiontree.absclass;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.absclass.impl.ClientStatusActiveNode;
import com.yurylink.decisiontree.absclass.impl.ClientLegalAgeNode;

public enum NodeTypes {
    LEGAL_AGE(new ClientLegalAgeNode()),
    ACTIVE_STATUS(new ClientStatusActiveNode());

    NodeTypes(Node node) {
    }

    Node node;

    public Node getNode() {
        return node;
    }

    public String evaluate(TreeContext content){
        return this.node.evaluate(content);
    }
}
