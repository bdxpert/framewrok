package com.framework.artisan.testing.core;

public class Asserts {
    public static void assertEquals(int x, int y) {
        if (x != y)
            System.out.println("Fail: result = "+x+" but expected "+y);
    }
}
