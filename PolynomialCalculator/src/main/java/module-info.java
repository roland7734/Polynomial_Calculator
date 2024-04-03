module com.example.polynomialcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.polynomialcalculator to javafx.fxml;
    exports com.example.polynomialcalculator.models;
    opens com.example.polynomialcalculator.models to javafx.fxml;
    exports com.example.polynomialcalculator.gui;
    opens com.example.polynomialcalculator.gui to javafx.fxml;
    exports com.example.polynomialcalculator.logic;
    opens com.example.polynomialcalculator.logic to javafx.fxml;
}