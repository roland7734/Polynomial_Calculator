package com.example.polynomialcalculator;

import static org.junit.jupiter.api.Assertions.*;

import com.example.polynomialcalculator.logic.PolynomialUtility;
import com.example.polynomialcalculator.models.Polynomial;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

public class PolynomialUtilityTest {

    @Test
    public void testAdd() {
        Polynomial addend1 = new Polynomial("3x^2 + 2x + 1");
        Polynomial addend2 = new Polynomial("5x^3 + 4x^2 + 2x + 3");

        Polynomial result = PolynomialUtility.add(addend1, addend2);

        assertEquals("5X^3+7X^2+4X+4", result.toString());
    }
    @Test
    public void testSubtract() {
        Polynomial minuend = new Polynomial("3x^2 + 2x + 1");
        Polynomial subtrahend = new Polynomial("x^2 + 3x + 2");

        Polynomial result = PolynomialUtility.subtract(minuend, subtrahend);

        assertEquals("2X^2-X-1", result.toString());
    }

    @Test
    public void testMultiply() {
        Polynomial factor1 = new Polynomial("3x^2 + 2x + 1");
        Polynomial factor2 = new Polynomial("2x + 1");

        Polynomial result = PolynomialUtility.multiply(factor1, factor2);

        assertEquals("6X^3+7X^2+4X+1", result.toString());
    }

    @Test
    public void testDivide() {
        Polynomial dividend = new Polynomial("6x^3 + 7x^2 + 4x + 1");
        Polynomial divisor = new Polynomial("2x + 1");

        Pair<Polynomial, Polynomial> result = PolynomialUtility.divide(dividend, divisor);

        assertEquals("3X^2+2X+1", result.getKey().toString());
        assertEquals("0", result.getValue().toString());
    }

    @Test
    public void testDerivate() {
        Polynomial operand = new Polynomial("3x^2 + 2x + 1");

        Polynomial result = PolynomialUtility.derivate(operand);

        assertEquals("6X+2", result.toString());
    }

    @Test
    public void testIntegrate() {
        Polynomial operand = new Polynomial("6x + 2");

        Polynomial result = PolynomialUtility.integrate(operand);

        assertEquals("3X^2+2X", result.toString());
    }
    @Test
    void validPolynomial1() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("2X^7 + 4x^3 - x^2 + x + 11"));
    }
    @Test
    void validPolynomial2() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X^2 + 7X - 5"));
    }

    @Test
    void validPolynomial3() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("5X^2 - X + 10"));
    }

    @Test
    void validPolynomialLeadingMinus() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("-X^2 + 3X - 1"));
    }


    @Test
    void validPolynomialSparse() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("2X^3 -4X^2 "));
    }

    @Test
    void validPolynomialMissingLastTerm() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("4X^4 + 2X^2 + X"));
    }

    @Test
    void validPolynomialTrailingSpace() {
        assertTrue(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X^2 + 7X - 5 "));
    }

    @Test
    void validPolynomialEmptyString() {
        assertTrue(PolynomialUtility.hasPolynomialFormat(""));
    }


    @Test
    void invalidPolynomialIncompleteExponent() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X^"));
    }

    @Test
    void invalidPolynomialMissingOperator() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X^2 7X^1 "));
    }

    @Test
    void invalidPolynomialMissingCaret() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X2 + 7X - 5"));
    }

    @Test
    void invalidPolynomialIncompleteTerm() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("2X^3 - 4X^2 + 7X -"));
    }

    @Test
    void invalidPolynomialInvalidVariable() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("2Y^3 - 4X^2 + 7X - 5"));
    }

    @Test
    void invalidPolynomialNonPolynomialString() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("abc"));
    }
    @Test
    void invalidPolynomialTwoLeadingMinuses() {
        assertFalse(PolynomialUtility.hasPolynomialFormat("--X^3"));
    }

}
