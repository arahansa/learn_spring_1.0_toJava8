package org.springframework.core;

import org.junit.Test;

/**
 * @author Lucas Choi
 */
public class Jdk18ControlFlowTests extends AbstractControlFlowTests{


    @Override
    protected ControlFlow createControlFlow() {
        return new ControlFlowFactory.Jdk18ControlFlow();
    }

    @Test
    public void testUnderClassAndMethod() {
        System.out.println("testUnderClassAndMethod");
        super.testUnderClassAndMethod();
    }

}
