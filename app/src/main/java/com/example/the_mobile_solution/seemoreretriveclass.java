package com.example.the_mobile_solution;

public class seemoreretriveclass {
    String Category_image_url,Category_Name;
    public seemoreretriveclass(){}

    public seemoreretriveclass(String category_image_url, String category_Name) {
        Category_image_url = category_image_url;
        Category_Name = category_Name;
    }

    public String getCategory_image_url() {
        return Category_image_url;
    }

    public void setCategory_image_url(String category_image_url) {
        Category_image_url = category_image_url;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }
}
