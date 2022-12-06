package tkshehan.c482software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tkshehan.c482software1.controller.MainController;
import tkshehan.c482software1.model.InhousePart;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 480);

        MainController controller = fxmlLoader.getController();
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        Part testPart = new InhousePart(1, "test", 1.0, 10, 1, 10, 1);
        partsList.add(testPart);

        ObservableList<Product> productsList = FXCollections.observableArrayList();

        controller.setState(partsList, productsList);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}