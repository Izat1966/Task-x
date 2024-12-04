package com.example.taskx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField annualSalaryField;
    @FXML private TextField hoursField;
    @FXML private TextField hourlyRateField;
    @FXML private TableView<Employee> tableView;
    @FXML private TableColumn<Employee, String> nameColumn;
    @FXML private TableColumn<Employee, String> typeColumn;
    @FXML private TableColumn<Employee, Double> salaryColumn;

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize the TableView
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        typeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getType()));
        salaryColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().calculateSalary()).asObject());

        tableView.setItems(employeeList);

        // Set up event listeners
        typeComboBox.getItems().addAll("Full-time", "Part-time", "Contractor");
        typeComboBox.setOnAction(e -> updateFieldsBasedOnType());
    }

    @FXML
    private void addEmployee() {
        String name = nameField.getText();
        String type = typeComboBox.getValue();

        if (name.isEmpty() || type == null) {
            showError("Please enter the name and select employee type.");
            return;
        }

        try {
            if (type.equals("Full-time")) {
                double annualSalary = Double.parseDouble(annualSalaryField.getText());
                if (annualSalary <= 0) {
                    showError("Annual Salary must be positive.");
                    return;
                }
                employeeList.add(new FullTimeEmployee(name, annualSalary));
            } else if (type.equals("Part-time")) {
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double hours = Double.parseDouble(hoursField.getText());
                if (hourlyRate <= 0 || hours <= 0) {
                    showError("Hourly rate and hours worked must be positive.");
                    return;
                }
                employeeList.add(new PartTimeEmployee(name, hourlyRate, hours));
            } else if (type.equals("Contractor")) {
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double maxHours = Double.parseDouble(hoursField.getText());
                if (hourlyRate <= 0 || maxHours <= 0) {
                    showError("Hourly rate and max hours must be positive.");
                    return;
                }
                employeeList.add(new com.example.demo.Contractor(name, hourlyRate, maxHours));
            }


            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numeric values.");
        }
    }

    private void updateFieldsBasedOnType() {
        String type = typeComboBox.getValue();

        // Hide fields that are not relevant to the selected employee type
        annualSalaryField.setVisible("Full-time".equals(type));
        hoursField.setVisible("Part-time".equals(type) || "Contractor".equals(type));
        hourlyRateField.setVisible("Part-time".equals(type) || "Contractor".equals(type));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nameField.clear();
        annualSalaryField.clear();
        hoursField.clear();
        hourlyRateField.clear();
        typeComboBox.setValue(null);
    }
}
