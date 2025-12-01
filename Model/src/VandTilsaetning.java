import java.util.ArrayList;
import java.util.List;

public class VandTilsaetning {
    private String vandTilsaetning;
    private double vandMaengde;
    private String vandKilde;
    private WhiskyProdukt whiskyProdukt;

    public VandTilsaetning(String vandTilsaetning, double vandMaengde,
                           String vandKilde, WhiskyProdukt whiskyProdukt) {
        this.vandTilsaetning = vandTilsaetning;
        this.vandMaengde = vandMaengde;
        this.vandKilde = vandKilde;
        this.whiskyProdukt = whiskyProdukt;
    }
}
