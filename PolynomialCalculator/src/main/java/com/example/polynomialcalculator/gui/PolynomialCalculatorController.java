package com.example.polynomialcalculator.gui;


import com.example.polynomialcalculator.logic.PolynomialUtility;
import com.example.polynomialcalculator.models.Polynomial;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class PolynomialCalculatorController {

    @FXML
    private TextField firstPolynomialTextField;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField resultTextField;

    @FXML
    private TextField secondPolynomialTextField;

    @FXML
    private Label remainderLabel;

    @FXML
    private TextField remainderPolynomialTextField;


    @FXML
    void onAddButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (validatePolynomials())
        {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial secondPolynomial = new Polynomial(secondPolynomialTextField.getText());
            firstPolynomialTextField.setText(firstPolynomial.toString());
            secondPolynomialTextField.setText(secondPolynomial.toString());
            Polynomial result = PolynomialUtility.add(firstPolynomial, secondPolynomial);
            resultLabel.setText("P(X) + Q(X) = ");
            resultTextField.setText(result.toString());
        }
    }


    @FXML
    void onSubtractButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (validatePolynomials())
        {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial secondPolynomial = new Polynomial(secondPolynomialTextField.getText());
            firstPolynomialTextField.setText(firstPolynomial.toString());
            secondPolynomialTextField.setText(secondPolynomial.toString());
            Polynomial result = PolynomialUtility.subtract(firstPolynomial, secondPolynomial);
            resultLabel.setText("P(X) - Q(X) = ");
            resultTextField.setText(result.toString());
        }
    }
    @FXML
    void onMultiplyButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (validatePolynomials())
        {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial secondPolynomial = new Polynomial(secondPolynomialTextField.getText());
            Polynomial result = PolynomialUtility.multiply(firstPolynomial, secondPolynomial);
            firstPolynomialTextField.setText(firstPolynomial.toString());
            secondPolynomialTextField.setText(secondPolynomial.toString());
            resultLabel.setText("P(X) * Q(X) = ");
            resultTextField.setText(result.toString());
        }
    }
    @FXML
    void onDivideButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (validatePolynomials())
        {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial secondPolynomial = new Polynomial(secondPolynomialTextField.getText());
            firstPolynomialTextField.setText(firstPolynomial.toString());
            secondPolynomialTextField.setText(secondPolynomial.toString());
            Polynomial quotient = new Polynomial(), remainder = new Polynomial();
            try {
                Pair<Polynomial, Polynomial> result = PolynomialUtility.divide(firstPolynomial, secondPolynomial);
                quotient = result.getKey();
                remainder = result.getValue();
            }catch (ArithmeticException e) {

                AlertUtility.openAlertError("Division by zero", "Cannot divide by Q = 0 polynomial.");
            }
            setRemainderTextFieldVisibility(true);
            resultLabel.setText("Quotient ");
            resultTextField.setText(quotient.toString());
            remainderLabel.setText("Remainder");
            remainderPolynomialTextField.setText(remainder.toString());

        }
    }

    @FXML
    void onDerivateButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (!PolynomialUtility.hasPolynomialFormat(firstPolynomialTextField.getText()))
        {
            AlertUtility.openAlertWarning("Invalid Polynomial Format","First Polynomial P=" + firstPolynomialTextField.getText() + " does not have a proper structure.");
        }
        else {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial result = PolynomialUtility.derivate(firstPolynomial);
            firstPolynomialTextField.setText(firstPolynomial.toString());
            resultLabel.setText("dP(x) / dx = ");
            resultTextField.setText(result.toString());
        }
    }

    @FXML
    void onIntegrateButtonClick() {
        setRemainderTextFieldVisibility(false);
        if (!PolynomialUtility.hasPolynomialFormat(firstPolynomialTextField.getText()))
        {
            AlertUtility.openAlertWarning("Invalid Polynomial Format","First Polynomial P=" + firstPolynomialTextField.getText() + " does not have a proper structure.");
        }
        else {
            Polynomial firstPolynomial = new Polynomial(firstPolynomialTextField.getText());
            Polynomial result = PolynomialUtility.integrate(firstPolynomial);
            firstPolynomialTextField.setText(firstPolynomial.toString());
            resultLabel.setText("∫ P(x) dx = ");
            if(result.getMonomials().isEmpty())
                resultTextField.setText("C");
            else {
                resultTextField.setText(result + "+C");
            }
        }
    }

    public void setRemainderTextFieldVisibility(boolean flag)
    {
        remainderLabel.setVisible(flag);
        remainderPolynomialTextField.setVisible(flag);
    }

    public boolean validatePolynomials() {
        if (!PolynomialUtility.hasPolynomialFormat(firstPolynomialTextField.getText())) {
            AlertUtility.openAlertWarning("Invalid Polynomial Format", "First Polynomial P=" + firstPolynomialTextField.getText() + " does not have a proper structure.");
            return false;
        }
        if (!PolynomialUtility.hasPolynomialFormat(secondPolynomialTextField.getText())) {
            AlertUtility.openAlertWarning("Invalid Polynomial Format", "Second Polynomial Q=" + secondPolynomialTextField.getText() + " does not have a proper structure.");
            return false;
        }
        return true;
    }
}
