package app.esarp.SCC_Health;

/**
 * Created by mrahman8 on 5/11/2017.
 */

public class Person {
    private String name;
    private String country;
    private String twitter;

    private String eoi;
    private String time;
    private String algorithm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEoi() {
        return eoi;
    }

    public void setEoi(String eoi) {
        this.eoi = eoi;
    }

    public String getTime() {
        return time;
    }
    public String getAlgorithm() {
        return algorithm;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Person [ID=" + name + ", GRID=" + country + ", Disease Type="
                + twitter + "]";
    }



}
