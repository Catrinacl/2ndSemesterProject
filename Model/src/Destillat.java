import java.util.List;

public class Destillat {
    private String destilatID;
    private String newMakeID;
    private double totalmaengeL;
    private double alkoholPc;
    private List<MaengdeDestilleret> maengder;

    public Destillat(String destilatID, String newMakeID, double totalmaengeL, double alkoholPc, List<MaengdeDestilleret> maengder) {
        this.destilatID = destilatID;
        this.newMakeID = newMakeID;
        this.totalmaengeL = totalmaengeL;
        this.alkoholPc = alkoholPc;
        this.maengder = maengder;
    }
}