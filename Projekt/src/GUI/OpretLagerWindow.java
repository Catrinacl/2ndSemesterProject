package GUI;

import Controller.Controller;
import Model.Lager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretLagerWindow extends Stage {

    private TextField txfLagerId = new TextField();
    private TextField txfLagerType = new TextField();
    private TextField txfAddresse = new TextField();
    private Button btnOpretLager = new Button("Opret Lager");


    public OpretLagerWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Lager");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Nyt Lager");
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));

        pane.add(new Label("Lager ID:"), 0, 1);
        pane.add(txfLagerId, 0, 2);
        pane.add(new Label("Lager Type:"), 0, 3);
        pane.add(txfLagerType, 0, 4);
        pane.add(new Label("Adresse:"), 0, 5);
        pane.add(txfAddresse, 0, 6);
        pane.add(btnOpretLager, 0, 7);
        btnOpretLager.setOnAction(event -> this.opretLagerAction());


    }

    private void opretLagerAction() {
        if (txfLagerId.getText().isEmpty() || txfLagerType.getText().isEmpty() || txfAddresse.getText().isEmpty()) {
            System.out.println("Alle felter skal udfyldes!");
            return;
        }
        Lager lager = Controller.createLager(txfLagerId.getText(), txfLagerType.getText(), txfAddresse.getText());
        this.close();
    }
}
