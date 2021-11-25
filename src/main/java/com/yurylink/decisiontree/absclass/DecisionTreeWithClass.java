package com.yurylink.decisiontree.absclass;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.common.node.NodeResultInNode;

public class DecisionTreeWithClass {

    private static DecisionTreeWithClass instance = null;

    private DecisionTreeWithClass() {
    }

    public static DecisionTreeWithClass getInstance(){
        if(instance == null){
            instance = new DecisionTreeWithClass();
        }
        return instance;
    }

    public String startDecisionTree(TreeContext content){
        String result = legalAgeConditionNode.evaluate(content);
        content.append("\n");
        content.append("RESULT: "+ result);
        return result;
    }

    private final Node hasNextPaymentsNode = Node.NodeBuilder.
            aNode().
            setDescription("Has Next Payments? ").
            setCondition(treeContext -> treeContext.getPromotion().getType() % 2 == 0).
            setPositiveOutcome(NodeResultInNode.SUCCESS.getNodeResults()).
            setNegativeOutcome(NodeResultInNode.ERROR_NO_NEXT_PAYMENT.getNodeResults()).
            build();

    private final Node isPromotionOddNode = Node.NodeBuilder.
            aNode().
            setDescription("Is promotion Type Odd? ").
            setCondition(treeContext -> treeContext.getPromotion().getType() % 2 == 0).
            setPositiveOutcome(hasNextPaymentsNode).
            setNegativeOutcome(NodeResultInNode.ERROR_PROMOTION_IS_IMPAR.getNodeResults()).
            build();

    private final Node isPromotionsRecentlyExpiredNode = Node.NodeBuilder.
            aNode().
            setDescription("Evaluating if the promotion is recently expired").
            setCondition(treeContext -> treeContext.getPromotion().getType() % 2 == 0).
            setPositiveOutcome(isPromotionOddNode).
            setNegativeOutcome(NodeResultInNode.OLD_PROMOTION.getNodeResults()).
            build();

    private final Node isPromotionsActiveNode = Node.NodeBuilder.
            aNode().
            setDescription("Evaluating if the user is active").
            setCondition(treeContext -> "ACTIVE".equals(treeContext.getStatus().getStatus())).
            setPositiveOutcome(isPromotionsRecentlyExpiredNode).
            setNegativeOutcome(NodeResultInNode.ERROR_INACTIVE_CLIENT.getNodeResults()).
            build();

    private final Node legalAgeConditionNode = Node.NodeBuilder.
            aNode().
            setDescription("Evaluating if the user is on legal age").
            setCondition(treeContext -> treeContext.getPromotion().getType() % 2 == 0).
            setPositiveOutcome(isPromotionsActiveNode).
            setNegativeOutcome(NodeResultInNode.ERROR_USER_MINOR.getNodeResults()).
            build();

}
