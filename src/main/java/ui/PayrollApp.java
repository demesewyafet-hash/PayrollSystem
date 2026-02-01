package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import repository.FileEmployeeRepository;
import service.PayrollService;
import service.EmployeeFactory;

public class PayrollApp extends Application {

    private UserRole role = UserRole.ADMIN; // Change to VIEWER to test viewer role
    private PayrollService payrollService;

    @Override
    public void start(Stage stage) {

        payrollService = new PayrollService(new FileEmployeeRepository());

        // 1️⃣ Employee input fields
        TextField idField = new TextField(); idField.setPromptText("ID");
        TextField nameField = new TextField(); nameField.setPromptText("Name");
        TextField salaryField = new TextField(); salaryField.setPromptText("Salary");
        TextField benefitField = new TextField(); benefitField.setPromptText("Benefits");
        TextField hoursField = new TextField(); hoursField.setPromptText("Hours");
        TextField rateField = new TextField(); rateField.setPromptText("Rate");

        // 2️⃣ Employee type ComboBox
        ComboBox<String> type = new ComboBox<>();
        type.getItems().addAll("FULL", "PART");
        type.setValue("FULL"); // default

        // 3️⃣ Buttons
        Button addBtn = new Button("Add Employee");
        Button deleteBtn = new Button("Delete Employee");

        // 4️⃣ Role-based GUI logic
        boolean isAdmin = role == UserRole.ADMIN;

        idField.setDisable(!isAdmin);
        nameField.setDisable(!isAdmin);
        salaryField.setDisable(!isAdmin);
        benefitField.setDisable(!isAdmin);
        hoursField.setDisable(!isAdmin);
        rateField.setDisable(!isAdmin);
        addBtn.setVisible(isAdmin);
        deleteBtn.setVisible(isAdmin);

        // 5️⃣ Dynamic field visibility based on employee type
        type.setOnAction(e -> {
            boolean isFull = type.getValue().equals("FULL");
            salaryField.setDisable(!isFull || !isAdmin);
            benefitField.setDisable(!isFull || !isAdmin);
            hoursField.setDisable(isFull || !isAdmin);
            rateField.setDisable(isFull || !isAdmin);
        });

        // 6️⃣ ListView to display employees
        ListView<Employee> listView = new ListView<>();
        listView.getItems().setAll(payrollService.getAllEmployees());

        // 7️⃣ Add Employee button action
        addBtn.setOnAction(e -> {
            try {
                Employee emp = EmployeeFactory.createEmployee(
                        type.getValue(),
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(salaryField.getText()),
                        Double.parseDouble(benefitField.getText()),
                        Integer.parseInt(hoursField.getText()),
                        Double.parseDouble(rateField.getText())
                );

                payrollService.addEmployee(emp);
                listView.getItems().setAll(payrollService.getAllEmployees());

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input! Please check all fields.");
                alert.show();
            }
        });

        // 8️⃣ Delete Employee button action
        deleteBtn.setOnAction(e -> {
            Employee emp = listView.getSelectionModel().getSelectedItem();
            if (emp != null) {
                payrollService.delete(emp.getId());
                // Refresh ListView after deletion
                listView.getItems().setAll(payrollService.getAllEmployees());
            }
        });

        // 9️⃣ Layout
        VBox root = new VBox(8,
                new Label("Role: " + role),
                type, idField, nameField, salaryField, benefitField, hoursField, rateField,
                addBtn, deleteBtn, listView
        );

        stage.setScene(new Scene(root, 400, 600));
        stage.setTitle("Payroll System");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

