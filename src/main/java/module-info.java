module tkshehan.c482software1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens tkshehan.c482software1 to javafx.fxml;
    exports tkshehan.c482software1;
    exports tkshehan.c482software1.controller;
    opens tkshehan.c482software1.controller to javafx.fxml;
}