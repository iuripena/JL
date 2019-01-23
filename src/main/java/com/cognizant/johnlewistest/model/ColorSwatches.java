package com.cognizant.johnlewistest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatches {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String color;
    private String rgbColor;
    private String skuid;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorSwatches)) return false;
        ColorSwatches that = (ColorSwatches) o;
        return Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getRgbColor(), that.getRgbColor()) &&
                Objects.equals(getSkuid(), that.getSkuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getRgbColor(), getSkuid());
    }

    @Override
    public String toString() {
        return "ColorSwatches{" +
                "color='" + color + '\'' +
                ", rgbColor='" + rgbColor + '\'' +
                ", skuid='" + skuid + '\'' +
                '}';
    }
}
