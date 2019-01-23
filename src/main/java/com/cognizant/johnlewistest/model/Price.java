package com.cognizant.johnlewistest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
    private String was;
    private String then1;
    private String then2;
    private String now;

    public String getWas() {
        return was;
    }

    public void setWas(String was) {
        this.was = was;
    }

    public String getThen1() {
        return then1;
    }

    public void setThen1(String then1) {
        this.then1 = then1;
    }

    public String getThen2() {
        return then2;
    }

    public void setThen2(String then2) {
        this.then2 = then2;
    }

    public String getNow() {
        return now;
    }

    public void setNow(Object now) {
        if (now instanceof String) {
            this.now = now.toString();
        } else {
            this.now = "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return Objects.equals(getWas(), price.getWas()) &&
                Objects.equals(getThen1(), price.getThen1()) &&
                Objects.equals(getThen2(), price.getThen2()) &&
                Objects.equals(getNow(), price.getNow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWas(), getThen1(), getThen2(), getNow());
    }

    @Override
    public String toString() {
        return "Price{" +
                "was='" + was + '\'' +
                ", then1='" + then1 + '\'' +
                ", then2='" + then2 + '\'' +
                ", now='" + now + '\'' +
                '}';
    }
}
