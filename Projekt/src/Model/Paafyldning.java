package Model;

import java.time.LocalDate;

public class Paafyldning {
   private String paafyldningsId;
   private double maengdeL;
   private double alkoholPcVedPaafyldning;
   private LocalDate dato;
   private LagerMedarbejder udfoertAf;

    public Paafyldning(String paafyldningsId, double maengdeL, double alkoholPcVedPaafyldning,
                       LocalDate dato, LagerMedarbejder udfoertAf) {
        this.paafyldningsId = paafyldningsId;
        this.maengdeL = maengdeL;
        this.alkoholPcVedPaafyldning = alkoholPcVedPaafyldning;
        this.dato = dato;
        this.udfoertAf = udfoertAf;
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

    @Override
    public String toString() {
        return paafyldningsId + " - " + dato + " - " + maengdeL + " L (" + alkoholPcVedPaafyldning + "%), udført af "
                + udfoertAf.getAntalPaafyldninger();
    }
}
