package tkshehan.c482software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tkshehan.c482software1.controller.MainController;
import tkshehan.c482software1.model.*;

import java.io.IOException;

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

        Part brakes = new InHouse(Inventory.getNextPartID(), "Brakes", 14.99, 5, 1, 15, 1);
        Part seat = new Outsourced( Inventory.getNextPartID(), "Seat", 29.99, 4, 1, 10, "Seaters");
        Part wheel = new InHouse(Inventory.getNextPartID(), "Wheel", 9.99, 15, 5, 20, 2);

        Inventory.addPart(brakes);
        Inventory.addPart(seat);
        Inventory.addPart(wheel);

        // Add parts for bikes
        Product bike = new Product(Inventory.getNextProductId(), "Road Bike", 5, 99.99, 1, 10);
        bike.addAssociatedPart(brakes);
        bike.addAssociatedPart(seat);
        bike.addAssociatedPart(wheel);
        bike.addAssociatedPart(wheel);

        Product uni = new Product(Inventory.getNextProductId(), "Unicycle", 2, 89.99, 1, 5);
        uni.addAssociatedPart(brakes);
        uni.addAssociatedPart(seat);
        uni.addAssociatedPart(wheel);

        Inventory.addProduct(bike);
        Inventory.addProduct(uni);

    }
}

