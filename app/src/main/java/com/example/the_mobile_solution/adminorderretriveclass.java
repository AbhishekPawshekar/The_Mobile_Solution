package com.example.the_mobile_solution;

public class adminorderretriveclass {
    String Product_Name, Product_Price, Product_discount, Price_To_Pay, Email, First_Name, Last_Name, Phone, Product_Image_url, Product_MoreImage_url1, Product_MoreImage_url2, Product_MoreImage_url3, Order_Count,Total_Charges,Address_To_Delivery,Delivery_Charges,Way_To_Delivery;

    public adminorderretriveclass() {

    }

    public adminorderretriveclass(String product_Name, String product_Price, String product_discount, String price_To_Pay, String email, String first_Name, String last_Name, String phone, String product_Image_url, String product_MoreImage_url1, String product_MoreImage_url2, String product_MoreImage_url3, String order_Count, String total_Charges, String address_To_Delivery, String delivery_Charges, String way_To_Delivery) {
        Product_Name = product_Name;
        Product_Price = product_Price;
        Product_discount = product_discount;
        Price_To_Pay = price_To_Pay;
        Email = email;
        First_Name = first_Name;
        Last_Name = last_Name;
        Phone = phone;
        Product_Image_url = product_Image_url;
        Product_MoreImage_url1 = product_MoreImage_url1;
        Product_MoreImage_url2 = product_MoreImage_url2;
        Product_MoreImage_url3 = product_MoreImage_url3;
        Order_Count = order_Count;
        Total_Charges = total_Charges;
        Address_To_Delivery = address_To_Delivery;
        Delivery_Charges = delivery_Charges;
        Way_To_Delivery = way_To_Delivery;
    }

    public String getTotal_Charges() {
        return Total_Charges;
    }

    public void setTotal_Charges(String total_Charges) {
        Total_Charges = total_Charges;
    }

    public String getAddress_To_Delivery() {
        return Address_To_Delivery;
    }

    public void setAddress_To_Delivery(String address_To_Delivery) {
        Address_To_Delivery = address_To_Delivery;
    }

    public String getDelivery_Charges() {
        return Delivery_Charges;
    }

    public void setDelivery_Charges(String delivery_Charges) {
        Delivery_Charges = delivery_Charges;
    }

    public String getWay_To_Delivery() {
        return Way_To_Delivery;
    }

    public void setWay_To_Delivery(String way_To_Delivery) {
        Way_To_Delivery = way_To_Delivery;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public String getProduct_discount() {
        return Product_discount;
    }

    public void setProduct_discount(String product_discount) {
        Product_discount = product_discount;
    }

    public String getPrice_To_Pay() {
        return Price_To_Pay;
    }

    public void setPrice_To_Pay(String price_To_Pay) {
        Price_To_Pay = price_To_Pay;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getProduct_Image_url() {
        return Product_Image_url;
    }

    public void setProduct_Image_url(String product_Image_url) {
        Product_Image_url = product_Image_url;
    }

    public String getProduct_MoreImage_url1() {
        return Product_MoreImage_url1;
    }

    public void setProduct_MoreImage_url1(String product_MoreImage_url1) {
        Product_MoreImage_url1 = product_MoreImage_url1;
    }

    public String getProduct_MoreImage_url2() {
        return Product_MoreImage_url2;
    }

    public void setProduct_MoreImage_url2(String product_MoreImage_url2) {
        Product_MoreImage_url2 = product_MoreImage_url2;
    }

    public String getProduct_MoreImage_url3() {
        return Product_MoreImage_url3;
    }

    public void setProduct_MoreImage_url3(String product_MoreImage_url3) {
        Product_MoreImage_url3 = product_MoreImage_url3;
    }

    public String getOrder_Count() {
        return Order_Count;
    }

    public void setOrder_Count(String order_Count) {
        Order_Count = order_Count;
    }
}