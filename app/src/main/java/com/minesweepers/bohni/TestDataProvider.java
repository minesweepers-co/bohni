package com.minesweepers.bohni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDataProvider {

    private final static List<String> testProductNames = Arrays.asList("samsung", "google", "apple",
            "facebook", "instagram", "zynga", "yahoo", "aol", "minesweepers");


    public static ArrayList<ProductModel> getTestProducts() {
        ArrayList products = new ArrayList<ProductModel>();

        for (int i=0; i<50; i++) {
            String product = testProductNames.get((int) (Math.random() * testProductNames.size()));
            ProductModel productModel = new ProductModel(product, "minesweepers", i, R.mipmap.logo  );
            products.add(productModel);
        }

        return products;
    }

}
