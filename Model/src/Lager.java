import java.util.ArrayList;
import java.util.List;

public class Lager {
    private String lagerId;
    private String lagerType;
    private String adresse;
    private List<Reol> reoler = new ArrayList<>();

    public Lager(String lagerId, String lagerType, String adresse, List<Reol> reoler) {
        this.lagerId = lagerId;
        this.lagerType = lagerType;
        this.adresse = adresse;
        this.reoler = reoler;
    }
}
