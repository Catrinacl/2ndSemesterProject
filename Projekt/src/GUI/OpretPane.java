package GUI;

import Controller.Controller;
import Model.Destillat;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class OpretPane extends GridPane {

    Button btnOpretFad = new Button("Opret");
    Button btnOpretDestillat = new Button("Opret");
    Button btnOpretReol = new Button("Opret");
    Button btnOpretLager = new Button("Opret");

    private TableView<Fad> tableViewFad;
    private TableView<Destillat> tableViewDestillat;


    public OpretPane() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        Label lblOverskrift = new Label("Hvad skal oprettes?");
        this.add(lblOverskrift, 0, 0);
        lblOverskrift.setFont(new Font(30));


        this.add(new Label("Opret Fad"), 0, 1);
        this.add(btnOpretFad, 0, 2);

        btnOpretFad.setOnAction(event -> {
            OpretFadWindow opretFadWindow = new OpretFadWindow();
            opretFadWindow.showAndWait();
            tableViewFad.getItems().setAll(Controller.getFade());
        });

        this.add(new Label("Opret Destillat"), 0, 4);
        this.add(btnOpretDestillat, 0, 5);

        btnOpretDestillat.setOnAction(event -> {
            OpretDestillatWindow opretDestillatWindow = new OpretDestillatWindow();
            opretDestillatWindow.showAndWait();
            tableViewDestillat.getItems().setAll(Controller.getDestillater());
        });

        this.add(new Label("Opret Lager"), 0, 7);
        this.add(btnOpretLager, 0, 8);

        btnOpretLager.setOnAction(event -> {
            OpretLagerWindow opretLagerWindow = new OpretLagerWindow();
            opretLagerWindow.showAndWait();
        });

        this.add(new Label("Opret Reol"), 0, 10);
        this.add(btnOpretReol, 0, 11);

        /*
        btnOpretReol.setOnAction(event -> {
            OpretReolWindow opretReolWindow = new OpretReolWindow();
            opretReolWindow.showAndWait();
        });
         */



    }

}
