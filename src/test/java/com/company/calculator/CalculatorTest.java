package com.company.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sergey on 13.05.2016.
 */
public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_EmptyString() throws Exception {
        calculator.calculate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_NullString() throws Exception {
        calculator.calculate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_DivisionByZero() throws Exception {
        calculator.calculate("0/0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculate_InvalidBrackets() throws Exception {
        calculator.calculate("((0+0)");
    }

    @Test
    public void testCalculate_InvalidString() throws Exception {
        try {
            calculator.calculate("()");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("2/");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("2+2+2+2+");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("-(2+2)");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("+(2+2)");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("*(2+2)");
        } catch (IllegalArgumentException ex) {
        }
        try {
            calculator.calculate("*+2as(2+2)");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testCalculate() throws Exception {
        double d;
        d = calculator.calculate("2+2");
        assertEquals(4, d, 0.05);
        d = calculator.calculate("-2+2");
        assertEquals(0, d, 0.05);
        d = calculator.calculate("(2+2)*4+6/(2-1)");
        assertEquals(22, d, 0.05);
        d = calculator.calculate("(-2+2)*4+6/(0-1)");
        assertEquals(-6, d, 0.05);
        d = calculator.calculate("(-2+-2)*4+6/(0-1)");
        assertEquals(-22, d, 0.05);
        d = calculator.calculate("(-2+2)*4+6/(0-1)");
        assertEquals(-6, d, 0.05);
        d = calculator.calculate("(-10/-5)");
        assertEquals(-15, d, 0.05);
        d = calculator.calculate("(-10/(-5))");
        assertEquals(2, d, 0.05);
        d = calculator.calculate("(-10/(-5))*0");
        assertEquals(0, d, 0.05);
        d = calculator.calculate("(-2+2)*4+-6/(0-1)");
        assertEquals(6, d, 0.05);
        d = calculator.calculate("((-2+2)*4+-6/(0-1))/20");
        assertEquals(0.3, d, 0.05);
        d = calculator.calculate("1+1+1+1+1+1+1+1+1+1+1+1+1+1+1++1+1+1+1+1+1+1++1+1+1+1+1++1+1+1+1+1+1+1");
        assertEquals(34, d, 0.05);
    }

}