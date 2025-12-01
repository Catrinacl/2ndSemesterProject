import java.util.ArrayList;
import java.util.List;

public class Hylde {
    private String hyldeId;
    private int kapacitet;
    private String placering;
    private String hyldeType;
    private List<Fad> fade = new ArrayList<>();
    private Reol reol;

    public Hylde(String hyldeId, int kapacitet, String placering, String hyldeType, List<Fad> fade, Reol reol) {
        this.hyldeId = hyldeId;
        this.kapacitet = kapacitet;
        this.placering = placering;
        this.hyldeType = hyldeType;
        this.fade = fade;
        this.reol = reol;
    }
}
