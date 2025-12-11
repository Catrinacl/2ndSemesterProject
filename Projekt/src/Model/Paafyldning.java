package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Paafyldning implements Serializable {
   private String paafyldningsId;
   private double maengdeL;
   private double alkoholPcVedPaafyldning;
   private LocalDate dato;
   private LagerMedarbejder udfoertAf;
   private Fad fad;
   private Destillat destillat;

    public Paafyldning(String paafyldningsId, double maengdeL, double alkoholPcVedPaafyldning,
                       LocalDate dato, LagerMedarbejder udfoertAf, Fad fad, Destillat destillat) {
        this.paafyldningsId = paafyldningsId;
        this.maengdeL = maengdeL;
        this.alkoholPcVedPaafyldning = alkoholPcVedPaafyldning;
        this.dato = dato;
        this.udfoertAf = udfoertAf;
        this.fad = fad;
        this.destillat = destillat;
    }

    public String getPaafyldningsId() {
        return paafyldningsId;
    }

    public double getMaengdeL() {
        return maengdeL;
    }

    public LocalDate getDato() {
        return dato;
    }

    public LagerMedarbejder getUdfoertAf() {
        return udfoertAf;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public Fad getFad() {
        return fad;
    }

    @Override
    public String toString() {
        return paafyldningsId + " - " + destillat.getDestillatID() + " - " + dato + " - " + maengdeL + " L (" + alkoholPcVedPaafyldning + "%), udført af "
                + udfoertAf.getNavn();
    }
}
