package Model;

import java.time.LocalDate;

public class Paafyldning {
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

    public double getAlkoholPcVedPaafyldning() {
        return alkoholPcVedPaafyldning;
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

    @Override
    public String toString() {
        return paafyldningsId + " - " + dato + " - " + maengdeL + " L (" + alkoholPcVedPaafyldning + "%), udført af "
                + udfoertAf.getAntalPaafyldninger();
    }
}
