package com.yurylink.decisiontree.absclass;

import com.yurylink.decisiontree.common.model.*;

import java.util.function.Predicate;

public class Node {

    private String description;
    private final Predicate<TreeContext> condition;
    private Node positiveOutcome;
    private Node negativeOutcome;

    public Node(String description, Predicate<TreeContext> condition, Node positiveOutcome, Node negativeOutcome) {
        this.description = description;
        this.condition = condition;
        this.positiveOutcome = positiveOutcome;
        this.negativeOutcome = negativeOutcome;
    }

    public String evaluate(TreeContext content){
        boolean result = this.condition.test(content);
        content.addNodeDescription(description, result);

        if(condition.test(content)){
            return positiveOutcome.evaluate(content);
        }else{
            return negativeOutcome.evaluate(content);
        }
    }


    public static final class NodeBuilder {
        private String description;
        private Predicate<TreeContext> condition;
        private Node positiveOutcome;
        private Node negativeOutcome;

        private NodeBuilder() {
        }

        public static NodeBuilder aNode() {
            return new NodeBuilder();
        }

        public NodeBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public NodeBuilder setCondition(Predicate<TreeContext> condition) {
            this.condition = condition;
            return this;
        }

        public NodeBuilder setPositiveOutcome(Node positiveOutcome) {
            this.positiveOutcome = positiveOutcome;
            return this;
        }

        public NodeBuilder setNegativeOutcome(Node negativeOutcome) {
            this.negativeOutcome = negativeOutcome;
            return this;
        }

        public Node build() {
            return new Node(description, condition, positiveOutcome, negativeOutcome);
        }
    }
}
