package GUI;

import Controller.Controller;
import Model.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProduktHistorikWindow extends Stage implements Observer {
    private final WhiskyProdukt whiskyProdukt;
    private ListView<VBox> listViewAftapninger;

    public ProduktHistorikWindow(WhiskyProdukt selectedProdukt) {
        this.whiskyProdukt = selectedProdukt;
        Controller.addObserver(this);

        this.setTitle("Historik for produkt " + whiskyProdukt.getProduktNr());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        Label lblInfoOmProdukt = new Label("Information om produktet");
        pane.add(lblInfoOmProdukt, 0, 0);
        lblInfoOmProdukt.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        pane.add(new Label("Produkt ID: " + whiskyProdukt.getProduktNr()), 0, 1);
        pane.add(new Label("Produkt Navn: " + whiskyProdukt.getNavn()), 0, 2);
        pane.add(new Label("Beskrivelse: " + whiskyProdukt.getBeskrivelse()), 0, 3);
        pane.add(new Label("Slut Alkohol %: " + whiskyProdukt.getSlutAlkoholProcent() + "%"), 0, 4);
        pane.add(new Label("Er den single cask: " + (whiskyProdukt.isErSingleCask()? "Ja" : "Nej")), 0, 5);
        pane.add(new Label("Antal flasker lavet: " + whiskyProdukt.getAntalFlasker()), 0, 6);

        String vandTilsætningText = (whiskyProdukt.getVandTilsaetning() != null)
                ? "Tilsat: " + whiskyProdukt.getVandTilsaetning().getVandMaengde() + " L - " + whiskyProdukt.getVandTilsaetning().getVandKilde()
                : "Ingen vandtilsætning";
        pane.add(new Label("Vandtilsætning:"), 1, 1);
        pane.add(new Label(vandTilsætningText), 1, 2);

        Label lblAftapning = new Label("Aftapningshistorik");
        lblAftapning.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        pane.add(lblAftapning, 0, 8);

        listViewAftapninger = new ListView<>();
        listViewAftapninger.setMaxHeight(400);
        listViewAftapninger.setPrefWidth(600);

        updateAftapningsList();

        pane.add(listViewAftapninger, 0, 9, 2, 1);

        Scene scene = new Scene(pane);
        this.setScene(scene);


    }

    private void updateAftapningsList() {
        listViewAftapninger.getItems().clear();

        if (whiskyProdukt.getAftapninger().isEmpty()) {
            VBox noItems = new VBox(new Label("Der er ikke registreret nogle aftapninger"));
            listViewAftapninger.getItems().add(noItems);
            return;
        }

        for (Aftapning aftapning : whiskyProdukt.getAftapninger()) {
            VBox aftapningDetaljer = new VBox();

            aftapningDetaljer.getChildren().add(new Label("Aftapning: " +
                    aftapning.getAftapningsNr() + ", Dato: " + aftapning.getDato()));

            aftapningDetaljer.getChildren().add(new Label("Volumen: " +
                    aftapning.getVolumenILiter() + " L, Alkohol: " +
                    aftapning.getAlkoholProcent() + "%"));


            Destillat destillat = aftapning.getDestillat();
            aftapningDetaljer.getChildren().add(new Label("------Destillat------"));
            aftapningDetaljer.getChildren().add(new Label("Destillat ID: " + destillat.getDestillatID() +
                            " (" + destillat.getAlkoholPc() + "%)"));

            Fad fad = findDestillatetsFad(destillat);
            aftapningDetaljer.getChildren().add(new Label("------Fad------"));
            if (fad != null) {
                aftapningDetaljer.getChildren().add(new Label("Fad ID: " + fad.getFadId() + " (" + fad.getTraeType() + ")"));

                aftapningDetaljer.getChildren().add(new Label("Påfyldninger:"));
                for (Paafyldning paafyldning : fad.getPaafyldninger()) {
                    aftapningDetaljer.getChildren().add(new Label(paafyldning.getDato() + " - " + paafyldning.getMaengdeL() + " L - udført af " + paafyldning.getUdfoertAf().getNavn()));
                }
            } else {
                aftapningDetaljer.getChildren().add(new Label("Ingen faddata"));
            }

            aftapningDetaljer.getChildren().add(new Label("------Destilleringer------"));

            for (MaengdeDestilleret md : destillat.getMaengdeDestilleret()) {
                Destillering destillering = md.getDestillering();

                aftapningDetaljer.getChildren().add(new Label(md.getLiter() + " L fra Destillering #"
                        + destillering.getDestilleringId() + " - " + destillering.getKornsort() +
                                ", MaltBatch: " + destillering.getMaltBatch()));
            }
            listViewAftapninger.getItems().add(aftapningDetaljer);
        }
    }

    private Fad findDestillatetsFad(Destillat destillat) {
        for (Fad fad : Controller.getFade()) {
            for (Paafyldning paafyldning : fad.getPaafyldninger()) {
                if (!fad.getPaafyldninger().isEmpty()) {
                    return fad;
                }
            }
        }
        return null;
    }

    @Override
    public void update() {
        updateAftapningsList();
    }
}
