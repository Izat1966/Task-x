module com.example.taskx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.taskx to javafx.fxml;
    exports com.example.taskx;
}