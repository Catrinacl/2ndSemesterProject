package GUI;

import Model.Destillering;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DestilleringDetaljeWindow extends Stage {
    public DestilleringDetaljeWindow(Destillering destillering) {
        this.setTitle("Detaljer for destillering: " + destillering.getDestilleringId());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Destillering id: " + destillering.getDestilleringId()), 0, 0);

        pane.add(new Label("Startdato: " + destillering.getStartDato()), 0, 1);

        pane.add(new Label("Slutdato: " + destillering.getSlutDato()), 0, 2);

        pane.add(new Label("Maltbatch: " + destillering.getMaltBatch()), 0, 3);

        pane.add(new Label("Kornsort: " + destillering.getKornsort()), 0, 4);

        String rygeTxt = (destillering.getRygemateriale() != null ? destillering.getRygemateriale() : "Ingen");
        pane.add(new Label("Rygemateriale: " + rygeTxt), 0, 5);

        String kommentarTxt = (destillering.getKommentar() != null ? destillering.getKommentar() : "");
        pane.add(new Label("Kommentar: " + kommentarTxt), 0, 6);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}
