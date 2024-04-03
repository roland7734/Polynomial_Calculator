package com.example.polynomialcalculator.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Polynomial {
    private HashMap<Integer, Double> monomials;

    public Polynomial() {
        this("");
    }

    public Polynomial(String inputString)
    {
        monomials = new HashMap<>();
        inputString = "+" + inputString;
        inputString = inputString.replaceAll(" ","");
        inputString = inputString.replaceAll("x","X");
        inputString = inputString.replaceAll("\\+X","+1X");
        inputString = inputString.replaceAll("-X","-1X");
        inputString = inputString.replaceAll("-","+-");
        String[] monomials = inputString.split("\\+");

        for (String monomial : monomials) {
            if(monomial.contains("X^"))
            {
                String[] numbers = monomial.split("X\\^");
                this.addTerm(Integer.parseInt(numbers[1]),this.getCoefficient(Integer.parseInt(numbers[1])) + Double.parseDouble(numbers[0]));
            }
            else if(monomial.contains("X"))
            {
                this.addTerm(1, this.getCoefficient(1) + Double.parseDouble(monomial.substring(0,monomial.length()-1)));
            }
            else if(!monomial.isEmpty()) {
                this.addTerm(0, this.getCoefficient(0) + Double.parseDouble(monomial));
            }
        }
    }

    public Double getCoefficient(int degree) {
        return this.monomials.getOrDefault(degree, 0.0);
    }

    public void addTerm(int degree, double coefficient) {
        if (coefficient != 0) {
            this.monomials.put(degree, coefficient);
        } else this.monomials.remove(degree);
    }

    public void setMonomials(HashMap<Integer, Double> monomials) {
        this.monomials = monomials;
    }

    public HashMap<Integer, Double> getMonomials() {
        return monomials;
    }

    public static String displayCoefficient(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        if (value.equals(1.0)) {
            return "";
        } else if (value.equals(-1.0)) {
            return "-";
        }
        return decimalFormat.format(value);
    }

    @Override
    public String toString() {
        if(this.monomials.isEmpty())
        {
            return "0";
        }
        String string = "";
        List<Integer> sortedKeys = new ArrayList<>(this.monomials.keySet());
        sortedKeys.sort(Collections.reverseOrder());
        for (int key : sortedKeys) {
            Double value = this.monomials.get(key);
            if (!value.equals(0.0)) {
                if (value > 0) {
                    if (!string.isEmpty())
                        string += "+";
                }
                if (key > 1)
                    string += displayCoefficient(value) + "X^" + key;
                else if (key == 1)
                    string += displayCoefficient(value) + "X";
                else {
                    if (value.equals(1.0)) string += "1";
                    else if (value.equals(-1.0)) string += "-1";
                    else
                        string += displayCoefficient(value);
                }
            }
        }
        return string;
    }
}