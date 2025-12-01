import java.util.ArrayList;
import java.util.List;

public class Fad {
    private String fadId;
    private double stoerrelseL;
    private String traeType;
    private String tidligereIndhold;
    private String status;
    private List<Paafyldning> paafyldninger = new ArrayList<>();
    private Hylde hylde;

    public Fad(String fadId, double stoerrelseL, String traeType, String tidligereIndhold, String status, List<Paafyldning> paafyldninger, Hylde hylde) {
        this.fadId = fadId;
        this.stoerrelseL = stoerrelseL;
        this.traeType = traeType;
        this.tidligereIndhold = tidligereIndhold;
        this.status = status;
        this.paafyldninger = paafyldninger;
        this.hylde = hylde;
    }
}
