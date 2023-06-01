package com.example.the_mobile_solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class after_buying_product_address_info extends AppCompatActivity {
String product_name,product_price,product_discount,productimageurl,productmoreimageurl1,productmoreimageurl2,productmoreimageurl3,saveto2,setcounter,phoneno,email,firstname_header,lastname1_header,product_details;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
TextView productprice,deliverycharges,totalprice;
RadioButton delivery,shop;
String shipping_charges,waytodelivery;
Button order;
int buy,a;
RadioGroup delivery_method;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_buying_product_address_info);
        Intent i1=getIntent();
        product_name=i1.getStringExtra("Product_Name");
        product_details=i1.getStringExtra("Product_Details");
        product_price=i1.getStringExtra("Product_Price");
        product_discount=i1.getStringExtra("Product_discount");
        saveto2=i1.getStringExtra("Price_To_Pay");
        email=i1.getStringExtra("Email");
        firstname_header=i1.getStringExtra("First_Name");
        lastname1_header=i1.getStringExtra("Last_Name");
        phoneno=i1.getStringExtra("Phone");
        setcounter=i1.getStringExtra("Order_Count");
        productimageurl=i1.getStringExtra("Product_Image_url");
        productmoreimageurl1=i1.getStringExtra("Product_MoreImage_url1");
        productmoreimageurl2=i1.getStringExtra("Product_MoreImage_url2");
        productmoreimageurl3=i1.getStringExtra("Product_MoreImage_url3");

        shop=findViewById(R.id.collect_form_shop);
        delivery=findViewById(R.id.home_delivery);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                after_clicking_home_delivery obj=new after_clicking_home_delivery();
                obj.collect_data_for_order( product_name,product_details,product_discount,product_price,saveto2,email,firstname_header,lastname1_header,phoneno,setcounter, productimageurl, productmoreimageurl1,productmoreimageurl2,productmoreimageurl3);
                getSupportFragmentManager().beginTransaction().replace(R.id.afterbuyingproductaddressframelayout,obj).commit();
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                after_clicking_shop_collect obj=new after_clicking_shop_collect();
                obj.collect_data_for_order( product_name,product_details,product_discount,product_price,saveto2,email,firstname_header,lastname1_header,phoneno,setcounter, productimageurl, productmoreimageurl1,productmoreimageurl2,productmoreimageurl3);
                getSupportFragmentManager().beginTransaction().replace(R.id.afterbuyingproductaddressframelayout,obj).commit();
            }
        });

}
}