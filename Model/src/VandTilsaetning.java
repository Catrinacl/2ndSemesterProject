import java.util.ArrayList;
import java.util.List;

public class VandTilsaetning {

    private String produktNr;
    private String navn;
    private String beskrivelse;
    private double slutAlkoholProcent;
    private boolean erSingleCask;
    private int antalFlasker;
    private VandTilsaetning vandTilsaetning;
    private List<Aftapning> aftapninger;

    public WhiskyProdukt(String produktNr, String navn, String beskrivelse, double slutAlkoholProcent,
                         boolean erSingleCask, int antalFlasker, VandTilsaetning vandTilsaetning) {
        this.produktNr = produktNr;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.slutAlkoholProcent = slutAlkoholProcent;
        this.erSingleCask = erSingleCask;
        this.antalFlasker = antalFlasker;
        this.vandTilsaetning = vandTilsaetning;
        this.aftapninger = new ArrayList<>();
    }

}
