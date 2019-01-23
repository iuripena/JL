package com.cognizant.johnlewistest.service;

import com.cognizant.johnlewistest.model.Inventory;
import com.cognizant.johnlewistest.model.Price;
import com.cognizant.johnlewistest.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${api.products}")
    private String productsURL;

    // The test was not able to get the URL form the properties file
    private String url = "https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";

    @Override
    public Inventory getProducts(final String labelType) {
        RestTemplate restTemplate = new RestTemplate();
        Inventory inventory = restTemplate.getForObject(url, Inventory.class);
        return processInventory(inventory, labelType);
    }

    private Inventory processInventory(final Inventory inventory, final String labelType) {
        Inventory processedInventory = new Inventory();
        processedInventory.setProducts(processRawProducts(inventory.getProducts(), labelType));
        return processedInventory;
    }

    private Product[] processRawProducts(final Product[] products, final String labelType) {
        List<Product> productList = new LinkedList<>();

        for (Product product : products) {

            if (!priceDiscount(product.getPrice()))
                continue;

            String formattedPrice = formatPrice(product.getPrice().getNow());
            product.setNowPrice(formattedPrice);

            String priceLabel = calculatePriceLabel(product.getPrice(), labelType == null ? "ShowWasNow" : labelType);
            product.setPriceLabel(priceLabel);

            productList.add(product);
        }

        productList.sort(Collections.reverseOrder());

        return productList.toArray(new Product[productList.size()]);
    }

    public String calculatePriceLabel(final Price price, final String labelType) {
        StringBuilder priceLabel;

        switch (labelType) {
            case "ShowWasThenNow":
                priceLabel = new StringBuilder("Was ");
                priceLabel.append(formatPrice(price.getWas()));
                if (!price.getThen2().equals("")) {
                    priceLabel.append(", then ");
                    priceLabel.append(formatPrice(price.getThen2()));
                } else if (!price.getThen1().equals("")) {
                    priceLabel.append(", then ");
                    priceLabel.append(formatPrice(price.getThen1()));
                }
                break;
            case "ShowPercDscount":
                float floatWas = Float.parseFloat(price.getWas());
                float floatNow = Float.parseFloat(price.getNow());
                int discount = (int) (100 * (floatWas - floatNow) / floatWas);
                priceLabel = new StringBuilder(discount);
                priceLabel.append("% off, ");
                break;
            default:
                priceLabel = new StringBuilder("Was ");
                priceLabel.append(formatPrice(price.getWas()));
        }
        priceLabel.append(", now ");
        priceLabel.append(formatPrice(price.getNow()));

        return priceLabel.toString();
    }

    private String formatPrice(final String nowPrice) {
        StringBuilder formattedPrice = new StringBuilder();

        if (!nowPrice.equals("")) {
            formattedPrice.append("£");
            String price = nowPrice;

            if (Float.valueOf(nowPrice) > 10) {
                price = String.valueOf(Float.valueOf(nowPrice).intValue());
            }
            formattedPrice.append(price);
        }

        return formattedPrice.toString();
    }

    private boolean priceDiscount(final Price price) {
        boolean onSale = true;

        if (price.getWas().equals("") || price.getNow().equals("")) {
            onSale = false;
        } else {
            float floatWas = Float.parseFloat(price.getWas());
            float floatNow = Float.parseFloat(price.getNow());

            if (floatNow >= floatWas)
                onSale = false;
        }

        return onSale;
    }
}
