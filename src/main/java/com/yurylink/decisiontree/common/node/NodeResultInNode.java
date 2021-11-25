package com.yurylink.decisiontree.common.node;

import com.yurylink.decisiontree.absclass.NodeResults;

public enum NodeResultInNode {

    SUCCESS(new NodeResults(NodeResult.ERROR_INACTIVE_CLIENT)),
    ERROR_INACTIVE_CLIENT(new NodeResults(NodeResult.ERROR_INACTIVE_CLIENT)),
    ERROR_USER_MINOR(new NodeResults(NodeResult.ERROR_USER_MINOR)),
    ERROR_PROMOTION_IS_IMPAR(new NodeResults(NodeResult.ERROR_PROMOTION_IS_IMPAR)),
    ERROR_NO_NEXT_PAYMENT(new NodeResults(NodeResult.ERROR_NO_NEXT_PAYMENT)),
    OLD_PROMOTION(new NodeResults(NodeResult.OLD_PROMOTION));

    private NodeResults nodeResults;

    NodeResultInNode(NodeResults nodeResults) {
        this.nodeResults = nodeResults;
    }

    public NodeResults getNodeResults() {
        return nodeResults;
    }
}
