import java.time.LocalDate;

public class Paafyldning {
   private String paafyldningstid;
   private double maengdeL;
   private double alkoholPcVedPaafyldning;
   private LocalDate dato;
   private LagerMedarbejder udfoertAf;

    public Paafyldning(String paafyldningstid, double maengdeL, double alkoholPcVedPaafyldning, LocalDate dato, LagerMedarbejder udfoertAf) {
        this.paafyldningstid = paafyldningstid;
        this.maengdeL = maengdeL;
        this.alkoholPcVedPaafyldning = alkoholPcVedPaafyldning;
        this.dato = dato;
        this.udfoertAf = udfoertAf;
    }
}
