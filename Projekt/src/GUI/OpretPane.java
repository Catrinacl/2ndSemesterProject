package GUI;

import Controller.Controller;
import Model.Destillat;
import Model.Destillering;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OpretPane extends GridPane {
    private TableView<Fad> tableViewFad = new TableView<>();
    private TableView<Destillat> tableViewDestillat = new TableView<>();
    private TableView<Destillering> tableViewDestillering = new TableView<>();

    public OpretPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        Label lblOverskrift = new Label("Hvad skal oprettes?");
        this.add(lblOverskrift, 0, 0, 3, 1);
        lblOverskrift.setFont(new Font(30));

        VBox vbox1 = new VBox(15);
        Button btnOpretMedarbejder = new Button("Opret");
        Button btnOpretDestillering = new Button("Opret");
        Button btnOpretDestillat = new Button("Opret");
        Button btnOpretPaafyldning = new Button("Opret");
        vbox1.getChildren().addAll(
                new Label("Opret Lagermedarbejder"), btnOpretMedarbejder,
                new Label("Opret Destillering"), btnOpretDestillering,
                new Label("Opret Destillat"), btnOpretDestillat,
                new Label("Opret Påfyldning"), btnOpretPaafyldning
        );

        VBox vbox2 = new VBox(15);
        Button btnOpretFad = new Button("Opret");
        Button btnOpretAftapning = new Button("Opret");
        Button btnOpretWhiskyProdukt = new Button("Opret");
        Button btnTilføjVandtilsaetning = new Button("Tilføj");
        vbox2.getChildren().addAll(
                new Label("Opret Fad"), btnOpretFad,
                new Label("Opret Aftapning"), btnOpretAftapning,
                new Label("Opret Whisky Produkt"), btnOpretWhiskyProdukt,
                new Label("Tilføj Vandtilsætning"), btnTilføjVandtilsaetning
        );

        VBox vbox3 = new VBox(15);
        Button btnOpretLager = new Button("Opret");
        Button btnOpretReol = new Button("Opret");
        Button btnOpretHylde = new Button("Opret");
        vbox3.getChildren().addAll(
                new Label("Opret Lager"), btnOpretLager,
                new Label("Opret og tilføj en reol til lager"), btnOpretReol,
                new Label("Opret og tilføj en hylde til reol"), btnOpretHylde
        );

        HBox hbox = new HBox(40);
        hbox.getChildren().addAll(vbox1, vbox2, vbox3);
        this.add(hbox, 0, 1);

        btnOpretMedarbejder.setOnAction(event -> {
            OpretMedarbejderWindow opretMedarbejderWindow = new OpretMedarbejderWindow();
            opretMedarbejderWindow.showAndWait();
        });

        btnOpretDestillering.setOnAction(event -> {
            OpretDestilleringWindow opretDestilleringWindow = new OpretDestilleringWindow();
            opretDestilleringWindow.showAndWait();
            tableViewDestillering.getItems().setAll(Controller.getDestilleringer());
        });

        btnOpretDestillat.setOnAction(event -> {
            OpretDestillatWindow opretDestillatWindow = new OpretDestillatWindow();
            opretDestillatWindow.showAndWait();
            tableViewDestillat.getItems().setAll(Controller.getDestillater());
        });

        btnOpretPaafyldning.setOnAction(event ->{
            OpretPaafyldningWindow opretPaafyldningWindow = new OpretPaafyldningWindow();
            opretPaafyldningWindow.showAndWait();
        });

        btnOpretFad.setOnAction(event -> {
            OpretFadWindow opretFadWindow = new OpretFadWindow();
            opretFadWindow.showAndWait();
            tableViewFad.getItems().setAll(Controller.getFade());
        });

        btnOpretAftapning.setOnAction(event ->{
            OpretAftapningWindow opretAftapningWindow = new OpretAftapningWindow();
            opretAftapningWindow.showAndWait();
        });

        btnOpretWhiskyProdukt.setOnAction(event -> {
            OpretWhiskyProduktWindow opretWhiskyProduktWindow = new OpretWhiskyProduktWindow();
            opretWhiskyProduktWindow.showAndWait();
        });

        btnTilføjVandtilsaetning.setOnAction(event -> {
            OpretVandtilsaetningWindow opretVandtilsaetningWindow = new OpretVandtilsaetningWindow();
            opretVandtilsaetningWindow.showAndWait();
        });

        btnOpretLager.setOnAction(event -> {
            OpretLagerWindow opretLagerWindow = new OpretLagerWindow();
            opretLagerWindow.showAndWait();
        });

        btnOpretReol.setOnAction(event -> {
            OpretReolWindow opretReolWindow = new OpretReolWindow();
            opretReolWindow.showAndWait();
        });

        btnOpretHylde.setOnAction(event ->{
            OpretHyldeWindow opretHyldeWindow = new OpretHyldeWindow();
            opretHyldeWindow.showAndWait();
        });

    }
}
