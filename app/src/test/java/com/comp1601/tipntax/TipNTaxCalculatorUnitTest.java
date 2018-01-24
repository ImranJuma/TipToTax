package com.comp1601.tipntax;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ldnel on 2018-01-10.
 */

public class TipNTaxCalculatorUnitTest {

    public static double toleranceForReals = 0.01;

    @Test
    public void negativeAmountTest() throws Exception {
        TipNTaxCalculator calculator = new TipNTaxCalculator();
        double amount = -100;
        double result = calculator.calculate(amount);
        double tolerance = toleranceForReals;
        assertEquals(result, TipNTaxCalculator.InvalidResult, tolerance);
    }
}
