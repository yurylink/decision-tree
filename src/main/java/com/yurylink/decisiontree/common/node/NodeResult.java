package com.yurylink.decisiontree.common.node;

public enum NodeResult {

    ERROR_INACTIVE_CLIENT(0, "Inactive_Client"),
    ERROR_USER_MINOR(1, "Client_is_minor"),
    ERROR_OFFERING_IS_IMPAR(2, "Impar_PromotionType"),
    ERROR_NO_NEXT_PAYMENT(3, "No_Scheduled_settlement");

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
