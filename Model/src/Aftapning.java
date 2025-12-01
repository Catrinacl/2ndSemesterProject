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
}
