package com.yurylink.decisiontree.functions;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.common.node.NodeResult;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class DecisionTreeWithFunctions {

    private static DecisionTreeWithFunctions instance = null;

    private DecisionTreeWithFunctions(){
    }

    public static DecisionTreeWithFunctions getInstance(){
        if(instance == null){
            instance = new DecisionTreeWithFunctions();
        }
        return instance;
    }

    public String startDecisionTree(TreeContext content){
        String result = legalAgeCondition.apply(content);
        content.append("\n");
        content.append("RESULT: "+ result);
        return result;
    }

    Predicate<TreeContext> isPromotionActive = nodeContentModel -> "ACTIVE".equals(nodeContentModel.getStatus().getStatus());
    Predicate<TreeContext> isUserLegalAge = nodeContentModel -> nodeContentModel.getClient().getAge() > 18 ;
    Predicate<TreeContext> isPromotionTypePar = nodeContentModel -> nodeContentModel.getPromotion().getType() % 2 == 0;
    Predicate<TreeContext> hasNextPayments = nodeContentModel -> nodeContentModel.getSettlement().getNextPayment().compareTo(BigDecimal.ZERO) >= 0;
    Predicate<TreeContext> hasTotalPayments = nodeContentModel -> nodeContentModel.getSettlement().getTotalValue().compareTo(BigDecimal.ZERO) >= 0;

    private final NodeFunction<TreeContext, String> hasNextPaymentsNode =
            nodeContentModel -> {
                nodeContentModel.addNodeDescription("Has Next Payments? ", hasNextPayments);
                return hasNextPayments.test(nodeContentModel) ?
                        "SUCCESS" :
                        NodeResult.ERROR_NO_NEXT_PAYMENT.name();
            };

    private final NodeFunction<TreeContext, String> isPromotionImparNode =
            nodeContentModel -> {
                nodeContentModel.addNodeDescription("Is promotion Type Impar? ", isPromotionTypePar);
                return isPromotionTypePar.test(nodeContentModel) ?
                        hasNextPaymentsNode.apply(nodeContentModel):
                        NodeResult.ERROR_OFFERING_IS_IMPAR.name();
    };

    private final NodeFunction<TreeContext, String> promotionsActiveNode =
            nodeContentModel -> {
                nodeContentModel.addNodeDescription("Evaluating if the promotion is active", isPromotionActive);

                return isPromotionActive.test(nodeContentModel) ?
                        isPromotionImparNode.apply(nodeContentModel) :
                        NodeResult.ERROR_INACTIVE_CLIENT.name() ;
            };

    private final NodeFunction<TreeContext, String> legalAgeCondition =
            nodeContentModel -> {
                nodeContentModel.addNodeDescription("Evaluating if the user is on legal age ", isUserLegalAge);
                return isUserLegalAge.test(nodeContentModel) ?
                        promotionsActiveNode.apply(nodeContentModel) :
                        NodeResult.ERROR_USER_MINOR.name();
            };
}

