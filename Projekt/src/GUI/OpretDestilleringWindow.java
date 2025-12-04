package GUI;

import Controller.Controller;
import Model.Destillering;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OpretDestilleringWindow extends Stage {
    private TextField txfDestilleringsId = new TextField();
    private TextField txfStartDato = new TextField();
    private TextField txfSlutDato = new TextField();
    private TextField txfMaltBatch = new TextField();
    private TextField txfKornsort = new TextField();
    private TextField txfRygemateriale = new TextField();
    private TextField txfKommentar = new TextField();
    private Button btnOpret = new Button("Opret Destillering");
    private ComboBox cbKornsort = new ComboBox<>();

    public OpretDestilleringWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Destillering");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Ny Destillering");
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));

        pane.add(new Label("Destillerings ID:"), 0, 1);
        pane.add(txfDestilleringsId, 0, 2);
        pane.add(new Label("Startdato:"), 0, 3);
        pane.add(txfStartDato, 0, 4);
        pane.add(new Label("Slutdato:"), 0, 5);
        pane.add(txfSlutDato, 0, 6);
        pane.add(new Label("Malt Batch:"), 0, 7);
        pane.add(txfMaltBatch, 0, 8);
        pane.add(new Label("Kornsort:"), 0, 9);
        cbKornsort.getItems().addAll("Byg", "Majs", "Hvede", "Rug");
        cbKornsort.setValue("---");
        pane.add(cbKornsort, 0, 10);
        pane.add(new Label("Rygemateriale:"), 0, 11);
        pane.add(txfRygemateriale, 0, 12);
        pane.add(new Label("Evt. kommentar:"), 0, 13);
        pane.add(txfKommentar, 0, 14);
        pane.add(btnOpret, 0, 15);
        btnOpret.setOnAction(event -> this.opretDestilleringAction());


    }

    private void opretDestilleringAction() {
        if (txfDestilleringsId.getText().isEmpty() || txfStartDato.getText().isEmpty() ||
                txfSlutDato.getText().isEmpty() || txfMaltBatch.getText().isEmpty() ||
                txfKornsort.getText().isEmpty() || txfRygemateriale.getText().isEmpty()) {
            System.out.println("Alle felter skal udfyldes!");
            return;
        }
        Destillering destillering = Controller.createDestillering(txfDestilleringsId.getText(),
                LocalDate.parse(txfStartDato.getText()), LocalDate.parse(txfSlutDato.getText()), txfMaltBatch.getText(),
                txfKornsort.getText(), txfRygemateriale.getText(), txfKommentar.getText());
        this.close();
    }
}
