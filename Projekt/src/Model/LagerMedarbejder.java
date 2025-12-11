package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LagerMedarbejder implements Serializable {
    private String medarbejderNr;
    private String navn;
    private List<Destillering> destilleringer;
    private List<Paafyldning> paafyldninger;

    public LagerMedarbejder(String medarbejderNr, String navn) {
        this.medarbejderNr = medarbejderNr;
        this.navn = navn;
        this.destilleringer = new ArrayList<>();
        this.paafyldninger = new ArrayList<>();
    }

    public void addDestillering(Destillering destillering) {
        this.destilleringer.add(destillering);
    }

    public void addPaafyldning(Paafyldning paafyldning) {
        this.paafyldninger.add(paafyldning);
    }

    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }

    public List<Paafyldning> getPaafyldninger() {
        return new ArrayList<>(paafyldninger);
    }

    public String getMedarbejderNr() {
        return medarbejderNr;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntalPaafyldninger() {
        return paafyldninger.size();
    }

    @Override
    public String toString() {
        int antal = paafyldninger != null ? paafyldninger.size() : 0;
        return medarbejderNr + " – " + navn + " (" + antal + " påfyldninger)";
    }
}
