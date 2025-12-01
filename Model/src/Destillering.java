import java.time.LocalDate;
import java.util.List;

public class Destillering {
    private String distilleringsID;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private String rygemateriale; //nullable?
    private String kommentar;
    private List<MaengdeDestilleret> maengder;

    public Destillering(String distilleringsID, LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, String rygemateriale, String kommentar, List<MaengdeDestilleret> maengder) {
        this.distilleringsID = distilleringsID;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.rygemateriale = rygemateriale;
        this.kommentar = kommentar;
        this.maengder = maengder;
    }
}
