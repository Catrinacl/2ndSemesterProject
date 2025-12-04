package GUI;

import Controller.Controller;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class OpretFadWindow extends Stage {
    private Fad fad;

    private TextField txfFadNr = new TextField();
    private TextField txfStoerrelseL = new TextField();
    private TextField txfTraeType = new TextField();
    private TextField txfTidligereIndhold = new TextField();
    private TextField txfStatus = new TextField();
    private TextField txfPaafyldninger = new TextField();
    private TextField txfHylde = new TextField();

    private final ListView<Fad> lvwFad = new ListView<>();
    private final ListView<Fad> lvwIndhold = new ListView<>();

    public OpretFadWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Fad");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Nyt Fad");
        pane.add(lblHeader, 0, 0, 2, 1);

        Label lblFade = new Label("Vælg fade:");
        pane.add(lblFade, 0, 1);
        pane.add(lvwFad, 0, 2, 1, 1);
        lvwFad.setPrefHeight(40);
        lvwFad.setPrefWidth(10);
        lvwFad.getItems().setAll(Controller.getFade());
        pane.add(new Label("Fad Nr:"), 0, 3);
        pane.add(txfFadNr, 1, 3);

        pane.add(new Label("Størrelse (L)"), 0, 5);
        pane.add(txfStoerrelseL, 1, 5);

        pane.add(new Label("Trætype"), 0, 6);
        pane.add(txfTraeType, 1, 6);

        pane.add(new Label("Tidligere Inhold:"), 0, 7);
        pane.add(txfTidligereIndhold, 1, 7);


        pane.add(new Label("Påfyldninger:"), 0, 8);
        pane.add(txfPaafyldninger, 1, 8);

        pane.add(new Label("Hylde:"), 0, 9);
        pane.add(txfHylde, 1, 9);

        Label lblIndhold = new Label("Valgt indhold:");
        pane.add(lblIndhold, 0, 10);
        pane.add(lvwIndhold, 0, 11);
        lvwIndhold.setPrefHeight(15);
        lvwIndhold.setPrefWidth(10);

        pane.add(txfStatus, 1, 11);
        pane.add(new Label("Status"), 2, 11);
    }
}
