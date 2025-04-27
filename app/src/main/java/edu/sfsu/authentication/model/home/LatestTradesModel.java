package edu.sfsu.authentication.model.home;

/* LatestTradesModel.java
 * Holds the data returned from the JSON request.
 * Patrick
 * */

public class LatestTradesModel {
    private String trades;
    private String c;
    private String i;
    private String p;
    private String s;
    private String t;
    private String x;
    private String z;

    public LatestTradesModel(String trades, String c, String i, String p, String s, String t, String x, String z) {
        this.trades = trades;
        this.c = c;
        this.i = i;
        this.p = p;
        this.s = s;
        this.t = t;
        this.x = x;
        this.z = z;
    }

    public LatestTradesModel() {}

    public String getTrades() {
        return trades;
    }

    public String getC() {
        return c;
    }

    public String getI() {
        return i;
    }

    public String getP() {
        return p;
    }

    public String getS() {
        return s;
    }

    public String getT() {
        return t;
    }

    public String getX() {
        return x;
    }

    public String getZ() {
        return z;
    }
}