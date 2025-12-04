package Model;

import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private String destilatID;
    private String newMakeID;
    private double totalmaengeL;
    private double alkoholPc;
    private ArrayList<MaengdeDestilleret> maengdeDestilleret;

    public Destillat(String destilatID, String newMakeID, double totalmaengeL, double alkoholPc) {
        this.destilatID = destilatID;
        this.newMakeID = newMakeID;
        this.totalmaengeL = totalmaengeL;
        this.alkoholPc = alkoholPc;
        this.maengdeDestilleret = new ArrayList<>();
    }

    public String getDestilatID() {
        return destilatID;
    }

    public String getNewMakeID() {
        return newMakeID;
    }

    public double getTotalmaengeL() {
        return totalmaengeL;
    }

    public double getAlkoholPc() {
        return alkoholPc;
    }

    public String getMaengder() {
        String result = "";

        if (maengdeDestilleret != null) {
            for (MaengdeDestilleret m : maengdeDestilleret) {
                result += m.getLiter() + " L fra destillering "
                        + m.getDestillering().getDistilleringsID() + "\n";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Destillat{" +
                "destilatID='" + destilatID + '\'' +
                ", newMakeID='" + newMakeID + '\'' +
                ", totalmaengeL=" + totalmaengeL +
                ", alkoholPc=" + alkoholPc +
                ", maengdeDestilleret=" + maengdeDestilleret +
                '}';
    }
}