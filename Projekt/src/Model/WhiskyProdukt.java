package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WhiskyProdukt implements Serializable {
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

    public String getProduktNr() {
        return produktNr;
    }

    public String getNavn() {
        return navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getSlutAlkoholProcent() {
        return slutAlkoholProcent;
    }

    public boolean isErSingleCask() {
        return erSingleCask;
    }

    public int getAntalFlasker() {
        return antalFlasker;
    }

    public VandTilsaetning getVandTilsaetning() {
        return vandTilsaetning;
    }

    public void setVandTilsaetning(VandTilsaetning vandTilsaetning) {
        this.vandTilsaetning = vandTilsaetning;
    }

    public List<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void setSlutAlkoholProcent(double slutAlkoholProcent) {
        this.slutAlkoholProcent = slutAlkoholProcent;
    }

    public void setAntalFlasker(int antalFlasker) {
        this.antalFlasker = antalFlasker;
    }

    public void setErSingleCask(boolean erSingleCask) {
        this.erSingleCask = erSingleCask;
    }

    public void addAftapning(Aftapning a) {
        aftapninger.add(a);
    }

    

    @Override

    public String toString() {
        return produktNr + " - " + navn;
    }

}
