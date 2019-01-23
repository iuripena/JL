package com.cognizant.johnlewistest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Comparable {
    private String productId;
    private String title;
    private ColorSwatches[] colorSwatches;
    private Price price;
    private String nowPrice;
    private String priceLabel;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ColorSwatches[] getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(ColorSwatches[] colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getProductId(), product.getProductId()) &&
                Objects.equals(getTitle(), product.getTitle()) &&
                Arrays.equals(getColorSwatches(), product.getColorSwatches()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getNowPrice(), product.getNowPrice()) &&
                Objects.equals(getPriceLabel(), product.getPriceLabel());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getProductId(), getTitle(), getPrice(), getNowPrice(), getPriceLabel());
        result = 31 * result + Arrays.hashCode(getColorSwatches());
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", title='" + title + '\'' +
                ", colorSwatches=" + Arrays.toString(colorSwatches) +
                ", price=" + price +
                ", nowPrice='" + nowPrice + '\'' +
                ", priceLabel='" + priceLabel + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Product product = (Product) o;
        float thatWas = Float.parseFloat(product.getPrice().getWas());
        float thatNow = Float.parseFloat(product.getPrice().getNow());
        int thatDisc = (int) (thatWas - thatNow);

        float thisWas = Float.parseFloat(price.getWas());
        float thisNow = Float.parseFloat(price.getNow());
        int thisDisc = (int) (thisWas - thisNow);

        if ((thisDisc - thatDisc) > 0)
            return 1;
        else
            return -1;
    }
}
