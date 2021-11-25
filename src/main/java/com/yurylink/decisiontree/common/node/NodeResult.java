package com.yurylink.decisiontree.common.node;

public enum NodeResult {

    SUCCESS(0, "Success"),
    ERROR_INACTIVE_CLIENT(0, "Inactive_Client"),
    ERROR_USER_MINOR(1, "Client_is_minor"),
    ERROR_PROMOTION_IS_IMPAR(2, "Impar_PromotionType"),
    ERROR_NO_NEXT_PAYMENT(3, "No_Scheduled_settlement"),
    OLD_PROMOTION(4, "No_longer_avlb");

    private int code;
    private String result;

    NodeResult(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
