import java.util.List;

public class LagerMedarbejder {
    private String medarbejderID;
    private String navn;
    private List<Paafyldning> paafyldninger;

    public LagerMedarbejder(String medarbejderID, String navn, List<Paafyldning> paafyldninger) {
        this.medarbejderID = medarbejderID;
        this.navn = navn;
        this.paafyldninger = paafyldninger;
    }




}
