package com.yurylink.decisiontree.common;

import com.yurylink.decisiontree.common.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class DecisionTreeTestHelper {

    public static TreeContext buildTreeContent(Client optClient,
                                               Settlement optSettlement,
                                               Promotion optPromotion,
                                               Status optStatus){
        return TreeContext.
                TreeContextBuilder.
                aNodeContentModel().
                setClient(Optional.ofNullable(optClient).orElse(getClientBasicMock())).
                setSettlement(Optional.ofNullable(optSettlement).orElse(getSettlementBasicMock())).
                setPromotions(Optional.ofNullable(optPromotion).orElse(getPromotionBasicMock())).
                setStatus(Optional.ofNullable(optStatus).orElse(getStatusBacisMock())).
                build();
    }

    public static Promotion getPromotionBasicMock(){
        Promotion promotion = new Promotion();
        promotion.setName("SomePromotion");
        promotion.setDue(LocalDate.now().minusDays(1));
        promotion.setType(10);
        return promotion;
    }

    public static Client getClientBasicMock(){
        Client client = new Client();
        client.setAge(19);
        client.setName("Jose");
        client.setId(123456);
        return client;
    }

    public static Settlement getSettlementBasicMock() {
        Settlement settlement = new Settlement();
        settlement.setId(123);
        settlement.setName("SomePurchase");
        settlement.setTotalValue(new BigDecimal("12000.00"));
        settlement.setNextPayment(new BigDecimal("1200.00"));
        return settlement;
    }

    public static Status getStatusBacisMock() {
        Status status = new Status();
        status.setStatus("ACTIVE");
        status.setType(2);
        return status;
    }
}
