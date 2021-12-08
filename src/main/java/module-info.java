module com.mycompany.webstudioproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.webstudioproject to javafx.fxml;
    
    exports com.mycompany.webstudioproject;
}
