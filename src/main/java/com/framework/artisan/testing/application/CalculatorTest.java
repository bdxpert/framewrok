package com.framework.artisan.testing.application;

import com.framework.artisan.testing.core.Before;
import com.framework.artisan.testing.core.Inject;
import com.framework.artisan.testing.core.Test;
import com.framework.artisan.testing.core.TestClass;

import static com.framework.artisan.testing.core.Asserts.assertEquals;

@TestClass
public class CalculatorTest {
    @Inject
    Calculator calculator;

    @Before
    public void init() {
//        calculator = new CalculatorImpl();
        calculator.reset();
    }

    @Test
    public void testMethodAddition() {
        System.out.println("Testing Addition..");
        assertEquals(calculator.add(3),3);
        assertEquals(calculator.add(6),9);
    }
    @Test
    public void testMethod2Subtraction() {
        System.out.println("Testing Subtraction..");
        assertEquals(calculator.add(3),3);
        assertEquals(calculator.subtract(6),-1);
    }
}
