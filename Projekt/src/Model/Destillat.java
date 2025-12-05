package Model;

import java.util.ArrayList;
import java.util.List;

public class Destillat {
    private String destillatID;
    private String newMakeID;
    private double totalmaengdeL;
    private double alkoholPc;
    private List<MaengdeDestilleret> maengdeDestilleret;

    public Destillat(String destillatID, String newMakeID, double totalmaengdeL, double alkoholPc) {
        this.destillatID = destillatID;
        this.newMakeID = newMakeID;
        this.totalmaengdeL = totalmaengdeL;
        this.alkoholPc = alkoholPc;
        this.maengdeDestilleret = new ArrayList<>();
    }

    public String getDestillatID() {
        return destillatID;
    }

    public String getNewMakeID() {
        return newMakeID;
    }

    public double getTotalmaengdeL() {
        return totalmaengdeL;
    }

    public double getAlkoholPc() {
        return alkoholPc;
    }

    public void addMaengdeDestilleret(MaengdeDestilleret m) {
        maengdeDestilleret.add(m);
    }

    public ArrayList<MaengdeDestilleret> getMaengdeDestilleret() {
        return new ArrayList<>(maengdeDestilleret);
    }

    public String getMaengderToString() {
        String result = "";

        if (maengdeDestilleret != null) {
            for (MaengdeDestilleret m : maengdeDestilleret) {
                result += m.getLiter() + " L fra destillering "
                        + m.getDestillering().getDestilleringId() + "\n";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return destillatID + " – Destillat (" + totalmaengdeL + " L, " + alkoholPc + "%)";
    }
}