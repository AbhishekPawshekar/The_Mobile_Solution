package com.example.the_mobile_solution;

public class retrivedataclass {
    String Product_Name,Product_Details,Product_Discount,Product_Price,Product_Price_For_Members,Delivery_Charges;
     String Product_image_url,Product_More_image_url1,Product_More_image_url2,Product_More_image_url3;
    retrivedataclass()
    {

    }

    public retrivedataclass(String product_Name, String product_Details, String product_Discount, String product_Price, String product_Price_For_Members, String delivery_Charges, String product_image_url, String product_More_image_url1, String product_More_image_url2, String product_More_image_url3) {
        Product_Name = product_Name;
        Product_Details = product_Details;
        Product_Discount = product_Discount;
        Product_Price = product_Price;
        Product_Price_For_Members = product_Price_For_Members;
        Delivery_Charges = delivery_Charges;
        Product_image_url = product_image_url;
        Product_More_image_url1 = product_More_image_url1;
        Product_More_image_url2 = product_More_image_url2;
        Product_More_image_url3 = product_More_image_url3;
    }

    public String getProduct_Price_For_Members() {
        return Product_Price_For_Members;
    }

    public void setProduct_Price_For_Members(String product_Price_For_Members) {
        Product_Price_For_Members = product_Price_For_Members;
    }

    public String getDelivery_Charges() {
        return Delivery_Charges;
    }

    public void setDelivery_Charges(String delivery_Charges) {
        Delivery_Charges = delivery_Charges;
    }

    public String getProduct_image_url() {
        return Product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        Product_image_url = product_image_url;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getProduct_Details() {
        return Product_Details;
    }

    public void setProduct_Details(String product_Details) {
        Product_Details = product_Details;
    }

    public String getProduct_Discount() {
        return Product_Discount;
    }

    public void setProduct_Discount(String product_Discount) {
        Product_Discount = product_Discount;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public String getProduct_More_image_url1() {
        return Product_More_image_url1;
    }

    public void setProduct_More_image_url1(String product_More_image_url1) {
        Product_More_image_url1 = product_More_image_url1;
    }

    public String getProduct_More_image_url2() {
        return Product_More_image_url2;
    }

    public void setProduct_More_image_url2(String product_More_image_url2) {
        Product_More_image_url2 = product_More_image_url2;
    }

    public String getProduct_More_image_url3() {
        return Product_More_image_url3;
    }

    public void setProduct_More_image_url3(String product_More_image_url3) {
        Product_More_image_url3 = product_More_image_url3;
    }
}
