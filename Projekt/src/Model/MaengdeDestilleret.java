package Model;

public class MaengdeDestilleret {
    private double liter;
    private Destillering destillering;
    private Destillat destillat;

    public MaengdeDestilleret(double liter, Destillering destillering, Destillat destillat) {
        this.liter = liter;
        this.destillering = destillering;
        this.destillat = destillat;
    }

    public double getLiter() {
        return liter;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    @Override
    public String toString() {
        return "MaengdeDestilleret{" +
                "liter=" + liter +
                ", destillering=" + destillering +
                ", destillat=" + destillat +
                '}';
    }
}
