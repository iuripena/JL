package com.cognizant.johnlewistest;

import com.cognizant.johnlewistest.controller.ProductsController;
import com.cognizant.johnlewistest.model.Inventory;
import com.cognizant.johnlewistest.model.Price;
import com.cognizant.johnlewistest.model.Product;
import com.cognizant.johnlewistest.service.ProductService;
import com.cognizant.johnlewistest.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductServiceImplTest {

    @Autowired
    private MockMvc mvcTest;

    @Autowired
    private ProductsController testController = new ProductsController();

    private Inventory response = new Inventory();
    private Product product = new Product();
    private Price price = new Price();

    @Mock
    private ProductService productService;
    private ProductServiceImpl psImpl = new ProductServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvcTest = MockMvcBuilders.standaloneSetup(testController).build();

        price.setNow("10.00");
        price.setThen1("9.00");
        price.setThen2("8.50");
        price.setWas("100.00");

        product.setNowPrice(price.getNow());
        product.setPrice(price);
        product.setPriceLabel("BigLabel");
        product.setProductId("WWWW");
        product.setTitle("WWW");
        Product [] myProducts = new Product[1];
        myProducts[0] = product;
        response.setProducts(myProducts);

    }

    @Test
    public void testRestController() {

        MvcResult response = null;
        try {
            response = (MvcResult) mvcTest.perform(get("/v1/products"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            System.out.println("Failure");
        }

        assertNull(response);
    }

    @Test
    public void testShowWasNow() {
        productService.getProducts("ShowWasNow");
        psImpl.getProducts(null);
        psImpl.getProducts("ShowWasNow");
        psImpl.getProducts("ShowWasThenNow");
        psImpl.getProducts("ShowPercDscount");
        psImpl.calculatePriceLabel(product.getPrice(), "ShowWasNow");
    }
}



