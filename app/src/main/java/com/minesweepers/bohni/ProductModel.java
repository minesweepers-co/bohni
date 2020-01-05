package com.minesweepers.bohni;

public class ProductModel {


    String name;
    String company;
    int id_;
    int image;

    public ProductModel(String name, String company, int id_, int image) {
        this.name = name;
        this.company = company;
        this.id_ = id_;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
