package com.framework.artisan.testing.application;

import com.framework.artisan.testing.core.Service;

@Service
public class CalculatorImpl implements Calculator {
    private int calcValue=0;

    public void reset() {
        calcValue=0;
    }

    public int add(int newValue) {
        calcValue=calcValue+newValue;
        return calcValue;
    }
    public int subtract(int newValue) {
        calcValue=calcValue-newValue;
        return calcValue;
    }
}
