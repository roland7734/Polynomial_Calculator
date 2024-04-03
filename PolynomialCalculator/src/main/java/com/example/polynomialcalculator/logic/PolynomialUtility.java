package com.example.polynomialcalculator.logic;

import com.example.polynomialcalculator.models.Polynomial;
import javafx.util.Pair;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialUtility {

    public static boolean hasPolynomialFormat(String input) {
        input = input.replaceAll("x","X");
        String polynomialPattern = "[+-]?(\\d*((X\\^\\d+)|X)|\\d+) *(([+-] *\\d+((X\\^\\d+)|X)? *)|([+-] *((X\\^\\d+)|X) *))*";
        Pattern pattern = Pattern.compile(polynomialPattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches() || input.isEmpty();
    }

    public static int degree(Polynomial p) {
        int max = 0;
        for (int degree : p.getMonomials().keySet()) {
            if (degree > max) {
                max = degree;
            }
        }
        return max;
    }

    public static Polynomial add(Polynomial addend1, Polynomial addend2)
    {
        Polynomial sum = new Polynomial();
        for(int degree : addend1.getMonomials().keySet())
        {
            if(addend2.getMonomials().containsKey(degree))
            {
                sum.addTerm(degree, addend1.getCoefficient(degree)+addend2.getCoefficient(degree));
            }
            else
            {
                sum.addTerm(degree,addend1.getCoefficient(degree));
            }
        }

        for(int degree : addend2.getMonomials().keySet())
        {
            if(!addend1.getMonomials().containsKey(degree))
            {
                sum.addTerm(degree,addend2.getCoefficient(degree));
            }
        }
        return sum;
    }

    public static Polynomial subtract(Polynomial minuend, Polynomial subtrahend)
    {
        for(int degree : subtrahend.getMonomials().keySet())
        {
            subtrahend.getMonomials().put(degree, -subtrahend.getCoefficient(degree));
        }
        return add(minuend,subtrahend);
    }


    public static Polynomial multiply(Polynomial multiplicand, Polynomial multiplier)
    {
        Polynomial product = new Polynomial();
        for (int degree1 : multiplicand.getMonomials().keySet()) {
            for (int degree2 : multiplier.getMonomials().keySet()) {
                int productDegree = degree1 + degree2;
                double productCoefficient = multiplicand.getCoefficient(degree1) * multiplier.getCoefficient(degree2);
                product.addTerm(productDegree, product.getCoefficient(productDegree) + productCoefficient);
            }
        }
        return product;
    }

    public static Pair<Polynomial,Polynomial> divide(Polynomial dividend, Polynomial divisor) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder;
        int degreeDividend = degree(dividend);
        int degreeDivisor = degree(divisor);

        if(divisor.getMonomials().isEmpty())
        {
            throw new ArithmeticException("Divide by zero polynomial");
        }

        while(degreeDividend>=degreeDivisor && !dividend.getMonomials().isEmpty()) {
            int degree = degreeDividend - degreeDivisor;
            double coefficient = dividend.getCoefficient(degreeDividend) / divisor.getCoefficient(degreeDivisor);
            quotient.addTerm(degree, coefficient);
            Polynomial monomial = new Polynomial();
            monomial.addTerm(degree, coefficient);
            dividend = subtract(dividend,multiply(divisor, monomial));
            degreeDividend=degree(dividend);
        }

        remainder = dividend;

        return new Pair<>(quotient,remainder);
    }
    public static Polynomial derivate(Polynomial operand)
    {
        Polynomial result = new Polynomial();
        for (int degree : operand.getMonomials().keySet()) {
            if (degree > 0) {
                double derivativeCoefficient = degree * operand.getCoefficient(degree);
                result.addTerm(degree - 1, derivativeCoefficient);
            }
        }
        return result;
    }

    public static Polynomial integrate(Polynomial integrand) {
        Polynomial result = new Polynomial();
        for (int degree : integrand.getMonomials().keySet()) {
            double integralCoefficient = integrand.getCoefficient(degree) / (degree + 1);
            result.addTerm(degree + 1, integralCoefficient);
        }
        return result;
    }



}
