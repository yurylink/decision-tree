package com.yurylink.decisiontree.functions;

import com.yurylink.decisiontree.common.model.Client;
import com.yurylink.decisiontree.common.model.Status;
import com.yurylink.decisiontree.common.model.TreeContext;
import com.yurylink.decisiontree.common.node.NodeResult;
import org.junit.jupiter.api.Test;

import static com.yurylink.decisiontree.common.DecisionTreeTestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionTreeWithFunctionsTest {

    @Test
    public void testDecisionTreeWithFunctions_Success(){
        TreeContext context = buildTreeContent(null, null, null, null);

        String result = DecisionTreeWithFunctions.getInstance().startDecisionTree(context);
        System.out.println(context.getCompleteLog());

        assertEquals(result, "SUCCESS");
    }

    @Test
    public void testDecisionTreeWithFunctions_Error_Minor(){
        Client client = getClientBasicMock();
        client.setAge(12);

        TreeContext context = buildTreeContent(client, null, null, null);

        String result = DecisionTreeWithFunctions.getInstance().startDecisionTree(context);
        System.out.println(context.getCompleteLog());

        assertEquals(result, NodeResult.ERROR_USER_MINOR.name());
    }

    @Test
    public void testDecisionTreeWithFunctions_Error_InactiveStatus(){
        Status status = getStatusBacisMock();
        status.setStatus("anything");

        TreeContext context = buildTreeContent(null, null, null, status);

        String result = DecisionTreeWithFunctions.getInstance().startDecisionTree(context);
        System.out.println(context.getCompleteLog());

        assertEquals(result, NodeResult.ERROR_INACTIVE_CLIENT.name());
    }
}