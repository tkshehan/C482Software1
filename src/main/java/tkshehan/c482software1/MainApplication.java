package tkshehan.c482software1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tkshehan.c482software1.controller.MainController;
import tkshehan.c482software1.model.InhousePart;
import tkshehan.c482software1.model.OutsourcedPart;
import tkshehan.c482software1.model.Part;
import tkshehan.c482software1.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 480);

        MainController controller = fxmlLoader.getController();
        initializeState(controller);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeState(MainController controller) {

        Part brakes = new InhousePart("Brakes", 14.99, 5, 1, 15, 1);
        Part seat = new OutsourcedPart( "Seat", 29.99, 4, 1, 10, "Seaters");
        Part wheel = new InhousePart("Wheel", 9.99, 15, 5, 20, 2);


        // Add parts for bikes
        new Product( "Road Bike", 5, 99.99, 1, 10,  new ArrayList<Part>(
                Arrays.asList( brakes, seat, wheel, wheel)
        ));
        new Product("Unicycle", 2, 89.99, 1, 5, new ArrayList<Part>(
                Arrays.asList(brakes, seat, wheel)
        ));

        controller.updateState();
    }
}

