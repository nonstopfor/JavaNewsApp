package com.java.zhangzhexin.model;

public class EpidemicDataCard {

    public String country = null;
    public String province = null;
    public String county = null;
    public Integer confirmed;
    public Integer suspected;
    public Integer cured;
    public Integer dead;

    EpidemicDataCard(String place, Integer confirmed, Integer suspected, Integer cured, Integer dead) {
        splitPlace(place);
        this.confirmed = confirmed;
        this.suspected = suspected;
        this.cured = cured;
        this.dead = dead;
    }

    private void splitPlace(String place) {
        String[] strings = place.split("\\|");
        country = strings[0];
        if (strings.length > 1) province = strings[1];
        if (strings.length > 2) county = strings[2];
    }

    public String displayMessageCountry() {
        StringBuilder result = new StringBuilder();
        result.append("country:" + country);
        result.append("\nconfirmed:" + confirmed);
        if (suspected != null) {
            result.append("\nsuspected:" + suspected);
        }
        if (cured != null) {
            result.append("\ncured:" + cured);
        }
        if (dead != null) {
            result.append("\ndead:" + dead);
        }
        return result.toString();
    }

    public String displayMessageProvince(){
        StringBuilder result = new StringBuilder();
        result.append("province:" + province);
        result.append("\nconfirmed:" + confirmed);
        if (suspected != null) {
            result.append("\nsuspected:" + suspected);
        }
        if (cured != null) {
            result.append("\ncured:" + cured);
        }
        if (dead != null) {
            result.append("\ndead:" + dead);
        }
        return result.toString();
    }
}
