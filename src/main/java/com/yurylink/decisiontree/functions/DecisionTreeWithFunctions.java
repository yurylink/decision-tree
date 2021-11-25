package com.yurylink.decisiontree.functions;

import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.common.node.NodeResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    Predicate<TreeContext> isUserActive = treeContext -> "ACTIVE".equals(treeContext.getStatus().getStatus());
    Predicate<TreeContext> isPromotionRecentlyExpired = treeContext -> ChronoUnit.DAYS.between(treeContext.getPromotion().getDue(), LocalDate.now()) <= 3;
    Predicate<TreeContext> isUserLegalAge = treeContext -> treeContext.getClient().getAge() > 18 ;
    Predicate<TreeContext> isPromotionTypePar = treeContext -> treeContext.getPromotion().getType() % 2 == 0;
    Predicate<TreeContext> hasNextPayments = treeContext -> treeContext.getSettlement().getNextPayment().compareTo(BigDecimal.ZERO) >= 0;

    private final NodeFunction<TreeContext, String> hasNextPaymentsNode =
            treeContext -> {
                treeContext.addNodeDescription("Has Next Payments? ", hasNextPayments);
                return hasNextPayments.test(treeContext) ?
                        "SUCCESS" :
                        NodeResult.ERROR_NO_NEXT_PAYMENT.name();
            };

    private final NodeFunction<TreeContext, String> isPromotionOddNode =
            treeContext -> {
                treeContext.addNodeDescription("Is promotion Type Odd? ", isPromotionTypePar);
                return isPromotionTypePar.test(treeContext) ?
                        hasNextPaymentsNode.apply(treeContext):
                        NodeResult.ERROR_PROMOTION_IS_IMPAR.name();
    };

    private final NodeFunction<TreeContext, String> promotionsRecentlyExpiredNode =
            treeContext -> {
                treeContext.addNodeDescription("Evaluating if the promotion is recently expired", isPromotionRecentlyExpired);

                return isPromotionRecentlyExpired.test(treeContext) ?
                        isPromotionOddNode.apply(treeContext) :
                        NodeResult.OLD_PROMOTION.name() ;
            };

    private final NodeFunction<TreeContext, String> promotionsActiveNode =
            treeContext -> {
                treeContext.addNodeDescription("Evaluating if the user is active", isUserActive);

                return isUserActive.test(treeContext) ?
                        isPromotionOddNode.apply(treeContext) :
                        NodeResult.ERROR_INACTIVE_CLIENT.name() ;
            };

    private final NodeFunction<TreeContext, String> legalAgeCondition =
            treeContext -> {
                treeContext.addNodeDescription("Evaluating if the user is on legal age ", isUserLegalAge);
                return isUserLegalAge.test(treeContext) ?
                        promotionsActiveNode.apply(treeContext) :
                        NodeResult.ERROR_USER_MINOR.name();
            };
}

