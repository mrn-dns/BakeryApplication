module com.example.ca_2_baking_information_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens com.example.ca_2_baking_information_system to javafx.fxml, xstream;
    opens controllers to javafx.fxml, xstream;
    opens models to javafx.fxml, xstream;
    opens utils to javafx.fxml, xstream;

    exports com.example.ca_2_baking_information_system;
    exports controllers;
    exports utils;
    exports models;


}

