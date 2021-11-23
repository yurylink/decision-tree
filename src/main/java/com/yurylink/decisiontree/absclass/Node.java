package com.yurylink.decisiontree.absclass;

import com.yurylink.decisiontree.common.model.*;

public abstract class Node {

    protected abstract String getNodeDescription();
    protected abstract boolean condition(TreeContext content);
    protected abstract String positiveOutcome(TreeContext content);
    protected abstract String negativeOutcome(TreeContext content);

    public String evaluate(TreeContext content){
        if(content.getCompleteLog().toString().isEmpty()){
            content.append("Stating the decision Tree: ");
        }

        content.append(" \n \\--- node: ").append(getNodeDescription());

        if(condition(content)){
            content.append(" - TRUE ");
            return positiveOutcome(content);

        }else{
            content.append(" - FALSE ");
            return negativeOutcome(content);
        }
    }
}
