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
import javafx.scene.text.Font;

public class OpretPane extends GridPane {

    Button btnOpretDestillering = new Button("Opret");
    Button btnOpretFad = new Button("Opret");
    Button btnOpretDestillat = new Button("Opret");
    Button btnOpretReol = new Button("Opret");
    Button btnOpretLager = new Button("Opret");
    Button btnOpretHylde = new Button("Opret");
    Button btnOpretWhiskyProdukt = new Button("Opret");
    Button btnOpretAftapning = new Button("Opret");



    private TableView<Fad> tableViewFad;
    private TableView<Destillat> tableViewDestillat;
    private TableView<Destillering> tableViewDestillering;


    public OpretPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        Label lblOverskrift = new Label("Hvad skal oprettes?");
        this.add(lblOverskrift, 0, 0);
        lblOverskrift.setFont(new Font(30));

        this.add(new Label("Opret Destillering"), 0, 1);
        this.add(btnOpretDestillering, 0, 2);

        btnOpretDestillering.setOnAction(event -> {
            OpretDestilleringWindow opretDestilleringWindow = new OpretDestilleringWindow();
            opretDestilleringWindow.showAndWait();
            tableViewDestillering.getItems().setAll(Controller.getDestilleringer());
        });

        this.add(new Label("Opret Fad"), 0, 3);
        this.add(btnOpretFad, 0, 4);

        btnOpretFad.setOnAction(event -> {
            OpretFadWindow opretFadWindow = new OpretFadWindow();
            opretFadWindow.showAndWait();
            tableViewFad.getItems().setAll(Controller.getFade());
        });

        this.add(new Label("Opret Destillat"), 0, 5);
        this.add(btnOpretDestillat, 0, 6);

        btnOpretDestillat.setOnAction(event -> {
            OpretDestillatWindow opretDestillatWindow = new OpretDestillatWindow();
            opretDestillatWindow.showAndWait();
            tableViewDestillat.getItems().setAll(Controller.getDestillater());
        });

        this.add(new Label("Opret Lager"), 1, 1);
        this.add(btnOpretLager, 1, 2);

        btnOpretLager.setOnAction(event -> {
            OpretLagerWindow opretLagerWindow = new OpretLagerWindow();
            opretLagerWindow.showAndWait();
        });

        this.add(new Label("Opret Reol"), 1, 3);
        this.add(btnOpretReol, 1, 4);


        btnOpretReol.setOnAction(event -> {
            OpretReolWindow opretReolWindow = new OpretReolWindow();
            opretReolWindow.showAndWait();
        });
        this.add(new Label("Opret Hylde"), 1,5);
        this.add(btnOpretHylde, 1,6);

        btnOpretHylde.setOnAction(event ->{
            OpretHyldeWindow opretHyldeWindow = new OpretHyldeWindow();
            opretHyldeWindow.showAndWait();
        });

        this.add(new Label("Opret Whisky Produkt"),0,7);
        this.add(btnOpretWhiskyProdukt,0 ,8 );

            btnOpretWhiskyProdukt.setOnAction(event -> {
            OpretWhiskyProduktWindow opretWhiskyProduktWindow = new OpretWhiskyProduktWindow();
            opretWhiskyProduktWindow.showAndWait();
        });

        this.add(new Label("Opret Aftapning"),1,7);
        this.add(btnOpretAftapning,1,8);

            btnOpretAftapning.setOnAction(event ->{
            OpretAftapningWindow opretAftapningWindow = new OpretAftapningWindow();
            opretAftapningWindow.showAndWait();
        });
    }
}
