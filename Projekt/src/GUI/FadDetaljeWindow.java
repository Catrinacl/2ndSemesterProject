package GUI;

import Controller.Controller;
import Model.Fad;
import Model.Paafyldning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FadDetaljeWindow extends Stage implements Observer {

    private final Fad fad;
    private VBox boxPaafyldninger;

    public FadDetaljeWindow(Fad fad) {
        this.fad = fad;
        Controller.addObserver(this);

        this.setTitle("Detaljer for Fad " + fad.getFadId());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Fad ID: " + fad.getFadId()), 0, 0);
        pane.add(new Label("Størrelse i Liter: " + fad.getStoerrelseL()), 0, 1);
        pane.add(new Label("Træ type: " + fad.getTraeType()), 0, 2);
        pane.add(new Label("Tidligere indhold: " + fad.getTidligereIndhold()), 0, 3);
        pane.add(new Label("Status: " + fad.getStatus()), 0, 4);

        Label lblPaafyldningsHistorik = new Label("Påfyldnings historik:");
        lblPaafyldningsHistorik.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        pane.add(lblPaafyldningsHistorik, 0, 6);

        boxPaafyldninger = new VBox(5);
        pane.add(boxPaafyldninger, 0, 7);

        updatePaafyldningsListe();

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void updatePaafyldningsListe() {
        boxPaafyldninger.getChildren().clear();

        if (fad.getPaafyldninger().isEmpty()) {
            boxPaafyldninger.getChildren().add(new Label("Ingen påfyldninger."));
            return;
        }

        for (Paafyldning p : fad.getPaafyldninger()) {
            boxPaafyldninger.getChildren().add(new Label(p.toString()));
        }
    }

    @Override
    public void update() {
        updatePaafyldningsListe();
    }
}
