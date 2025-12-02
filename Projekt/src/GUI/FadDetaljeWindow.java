package GUI;

import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FadDetaljeWindow extends Stage {

    public FadDetaljeWindow(Fad fad) {
        this.setTitle("Detaljer for Fad " + fad.getFadId());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Fad ID: " + fad.getFadId()), 0, 0);
        pane.add(new Label("Størrelse i Liter: " + fad.getStoerrelseL()), 0, 1);
        pane.add(new Label("Træ type: " + fad.getTraeType()), 0, 2);
        pane.add(new Label("Tidligere indhold: " + fad.getTidligereIndhold()), 0, 3);
        pane.add(new Label("Status: " + fad.getStatus()), 0, 4);
    }

}
