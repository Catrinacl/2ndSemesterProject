import java.util.ArrayList;
import java.util.List;

public class Reol {
    private String reolId;
    private String reolType;
    private List<Hylde> hylder = new ArrayList<>();
    private Lager lager;

    public Reol(String reolId, String reolType, List<Hylde> hylder, Lager lager) {
        this.reolId = reolId;
        this.reolType = reolType;
        this.hylder = hylder;
        this.lager = lager;
    }
}
