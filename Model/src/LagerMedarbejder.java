import java.util.ArrayList;
import java.util.List;

public class LagerMedarbejder {
    private String medarbejderNr;
    private String navn;
    private List<Paafyldning> paafyldninger;

    public LagerMedarbejder(String medarbejderNr, String navn) {
        this.medarbejderNr = medarbejderNr;
        this.navn = navn;
        this.paafyldninger = new ArrayList<>();
    }

    public void addPaafyldning(Paafyldning paafyldning) {
        this.paafyldninger.add(paafyldning);
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

    // antal paafyldninger udført
    public int getAntalPaafyldninger() {
        return paafyldninger.size();
    }

    @Override
    public String toString() {
        return "LagerMedarbejder{" +
                "medarbejderNr='" + medarbejderNr + '\'' +
                ", navn='" + navn + '\'' +
                ", paafyldninger=" + paafyldninger +
                '}';
    }
}
