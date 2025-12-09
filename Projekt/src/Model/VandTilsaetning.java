package Model;

public class VandTilsaetning {
    private String vandTilsaetningId;
    private double vandMaengdeL;
    private String vandKilde;
    private WhiskyProdukt whiskyProdukt;

    public VandTilsaetning(String vandTilsaetningId, double vandMaengdeL,
                           String vandKilde, WhiskyProdukt whiskyProdukt) {
        this.vandTilsaetningId = vandTilsaetningId;
        this.vandMaengdeL = vandMaengdeL;
        this.vandKilde = vandKilde;
        this.whiskyProdukt = whiskyProdukt;
    }

    public String getVandTilsaetningId() {
        return vandTilsaetningId;
    }

    public double getVandMaengdeL() {
        return vandMaengdeL;
    }

    public String getVandKilde() {
        return vandKilde;
    }

    public WhiskyProdukt getWhiskyProdukt() {
        return whiskyProdukt;
    }

    @Override
    public String toString() {
        String produktId = (whiskyProdukt != null ? whiskyProdukt.getProduktNr() : "ukendt produkt");

        return vandTilsaetningId + " - " + vandKilde +
                " (" + vandMaengdeL + " L, " + produktId + ")";
    }
}
