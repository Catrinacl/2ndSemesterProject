package Model;

import java.time.LocalDate;

public class Aftapning {
    private String aftapningsNr;
    private double alkoholProcent;
    private LocalDate dato;
    private double volumenILiter;
    private Destillat destillat;
    private WhiskyProdukt whiskyProdukt;

    public Aftapning(String aftapningsNr, double alkoholProcent,
                     LocalDate dato, double volumenILiter, Destillat destillat, WhiskyProdukt whiskyProdukt) {
        this.aftapningsNr = aftapningsNr;
        this.alkoholProcent = alkoholProcent;
        this.dato = dato;
        this.volumenILiter = volumenILiter;
        this.destillat = destillat;
        this.whiskyProdukt = whiskyProdukt;
    }

    public String getAftapningsNr() {
        return aftapningsNr;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getVolumenILiter() {
        return volumenILiter;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public WhiskyProdukt getWhiskyProdukt() {
        return whiskyProdukt;
    }

    @Override
    public String toString() {
        return aftapningsNr + " - " + dato + " - " + volumenILiter + " L";
    }

}
