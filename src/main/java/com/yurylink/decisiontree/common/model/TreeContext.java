package com.yurylink.decisiontree.common.model;

import java.util.function.Predicate;

public class TreeContext {

    private final Client client;
    private final Settlement settlement;
    private final Promotion promotion;
    private final Status status;

    private final StringBuilder log;
    private int nodeCount;
    private static final String SPACES = "    ";

    public TreeContext(Client client, Settlement settlement, Promotion promotion, Status status) {
        this.client = client;
        this.settlement = settlement;
        this.promotion = promotion;
        this.status = status;

        this.log = new StringBuilder("");
        this.nodeCount=0;
        this.append("Stating the decision Tree: ");
    }

    public Client getClient() {
        return client;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Status getStatus() {
        return status;
    }

    public String getCompleteLog(){
        return this.log.toString();
    }

    public StringBuilder append(String text){
        return addTabbing().append(text);
    }

    public void addNodeDescription(String description){
        this.nodeCount++;
        this.addTabbing().append(description);
    }

    public void addNodeDescription(String description, Predicate<TreeContext> condition){
        this.nodeCount++;
        this.append("\n");
        this.addTabbing().
                append("+--- node:").
                append(description).
                append(" = ").
                append(condition.test(this));
    }

    public void addNodeDescription(String description, Boolean conditionResult){
        this.nodeCount++;
        this.append("\n");
        this.addTabbing().
                append("+--- node:").
                append(description).
                append(" = ").
                append(conditionResult);
    }

    private StringBuilder addTabbing(){
        for (int i = 0; i < this.nodeCount; i++) {
            this.log.append(SPACES);
        }
        return this.log;
    }

    public static final class TreeContextBuilder {
        private Client client;
        private Settlement settlement;
        private Promotion promotion;
        private Status status;

        private TreeContextBuilder() {
        }

        public static TreeContextBuilder aNodeContentModel() {
            return new TreeContextBuilder();
        }

        public TreeContextBuilder setClient(Client client) {
            this.client = client;
            return this;
        }

        public TreeContextBuilder setSettlement(Settlement settlement) {
            this.settlement = settlement;
            return this;
        }

        public TreeContextBuilder setPromotions(Promotion promotion) {
            this.promotion = promotion;
            return this;
        }

        public TreeContextBuilder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public TreeContext build() {
            return new TreeContext(client, settlement, promotion, status);
        }
    }
}
