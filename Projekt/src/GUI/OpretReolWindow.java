package GUI;

import Controller.Controller;

import Model.Hylde;
import Model.Lager;
import Model.Reol;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class OpretReolWindow extends Stage {
    private Reol reol;

    private TextField txfReolId;
    private TextField txfReolType;
    private TextField txfHylde;
    private TextField txfLager;

    private final ListView<Reol> lvwReol = new ListView<>();
    private final ListView<Reol> lvwIndhold = new ListView<>();

    private Button btnOpret = new Button("Opret Reol");

    public OpretReolWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Reol");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        txfReolId = new TextField();
        txfReolType = new TextField();
        txfHylde = new TextField();
        txfLager = new TextField();


        Label lblHeader = new Label("Opret Ny Reol");
        pane.add(lblHeader, 0, 0, 2, 1);

        Label lblReol = new Label("Vælg Reol");
        pane.add(lblReol, 0, 1);
        pane.add(lvwReol, 0, 2, 1, 1);
        lvwReol.setPrefHeight(40);
        lvwReol.setPrefWidth(10);
        lvwReol.getItems().setAll(Controller.getReoler());

        pane.add(new Label("Reol Id:"), 0, 3);
        pane.add(txfReolId, 1, 3);

        pane.add(new Label("Reol Type:"), 0, 5);
        pane.add(txfReolType, 1, 5);

        pane.add(new Label("Hylde:"), 0, 6);
        pane.add(txfHylde, 1, 6);

        pane.add(new Label("Lager:"), 0, 7);
        pane.add(txfLager, 1, 7);

        Label lblIndhold = new Label("Valgt indhold:");
        pane.add(lblIndhold, 0, 10);
        pane.add(lvwIndhold, 0, 11);
        lvwIndhold.setPrefHeight(15);
        lvwIndhold.setPrefWidth(10);



        pane.add(btnOpret, 0, 12);
        btnOpret.setOnAction(event -> this.opretReolAction());
    }

    private void opretReolAction() {
        String reolId =  txfReolId.getText().trim();
        String reolType = txfReolType.getText().trim();
        ArrayList<Hylde> hylder = new ArrayList<>();
        Lager lager = null;

        reol = Controller.createReol(
                reolId,
                reolType,
                hylder,
                lager
        );
    }

    public Reol getReol() {
        return reol;
    }
}
