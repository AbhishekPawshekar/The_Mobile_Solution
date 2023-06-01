package com.example.the_mobile_solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class extra_product_details extends AppCompatActivity {

    TextView full_name, product_all_price, product_all_discount,your_price,saveupto,signupactionbar,loginactionbar,displaydetails;
    ImageSlider extra_all_image;
    Button cart;
    FirebaseAuth fba=FirebaseAuth.getInstance();
    FirebaseUser fbu;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
int ordercounter,pricecounter;
    FirebaseDatabase database;

String firstname_header,product_details,lastname1_header,product_name,product_price,product_discount,saveto2,phoneno,email,productimageurl,productmoreimageurl1,productmoreimageurl2,productmoreimageurl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_product_details);
        Intent i = getIntent();
        product_details=i.getStringExtra("Product_Details");
        product_name = i.getStringExtra("Product_Name");
        product_price = i.getStringExtra("Product_Price");
        product_discount = i.getStringExtra("Product_Discount");
        productimageurl = i.getStringExtra("Product_image_url");
        productmoreimageurl1 = i.getStringExtra("Product_More_image_url1");
        productmoreimageurl2 = i.getStringExtra("Product_More_image_url2");
        productmoreimageurl3 = i.getStringExtra("Product_More_image_url3");
        signupactionbar = findViewById(R.id.signupbar);
        loginactionbar = findViewById(R.id.loginbar);
        your_price = findViewById(R.id.displayyourprice);




        full_name = findViewById(R.id.displayproductname);
        product_all_price = findViewById(R.id.displayproductprice);
        product_all_discount = findViewById(R.id.displayproductdiscount);
        extra_all_image = findViewById(R.id.extra_image_product);
        displaydetails=findViewById(R.id.displayproductdetails);
        cart = findViewById(R.id.submitButtoncart);
        saveupto = findViewById(R.id.displayyou_save_uptoentry);

displaydetails.setText(product_details);

//setting values to views
        full_name.setText(product_name);
        product_all_price.setText(product_price);
        product_all_discount.setText("Discount:-" + product_discount + "%");
        int pp = Integer.parseInt(product_price);
        int pd = Integer.parseInt(product_discount);

        int pricetopay = (pp * pd) / 100;
        int saveto = pp - pricetopay;
        your_price.setText("Your Price:-" + saveto);
        saveto2 = String.valueOf(saveto);

        String saveto1 = String.valueOf(pricetopay);
        saveupto.setText(saveto1);

        List<SlideModel> imagelist = new ArrayList<>();
        extra_all_image.setImageList(checkimagestatus(imagelist,productimageurl,productmoreimageurl1,productmoreimageurl2,productmoreimageurl3), false);

            signupactionbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent j = new Intent(getApplicationContext(), registration_page.class);
                    startActivity(j);
                }
            });
            loginactionbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), login_page.class);
                    startActivity(i);
                }
            });
            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fbu=fba.getCurrentUser();

                    if(fbu!=null){
                        email=fbu.getEmail();
                        fbfs.collection("Customer").document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    firstname_header = (String) task.getResult().get("First_Name");
                                    lastname1_header = (String) task.getResult().get("Last_Name");
                                    phoneno = (String) task.getResult().get("Phone");
                                    String counter = (String) task.getResult().get("Order_Count");
                                    ordercounter = Integer.parseInt(counter);


                                String setcounter=String.valueOf(ordercounter+1);
                                Intent i1 = new Intent(getApplicationContext(), after_buying_product_address_info.class);
                                i1.putExtra("Product_Name", product_name);
                        i1.putExtra("Product_Price", product_price);
                        i1.putExtra("Product_Details",product_details);
                        i1.putExtra("Product_discount", product_discount);
                        i1.putExtra("Price_To_Pay", saveto2);
                        i1.putExtra("Email", email);
                        i1.putExtra("First_Name", firstname_header);
                        i1.putExtra("Last_Name", lastname1_header);
                        i1.putExtra("Phone", phoneno);
                        i1.putExtra("Order_Count", setcounter);
                        i1.putExtra("Product_Image_url", productimageurl);
                        i1.putExtra("Product_MoreImage_url1", productmoreimageurl1);
                        i1.putExtra("Product_MoreImage_url2", productmoreimageurl2);
                        i1.putExtra("Product_MoreImage_url3", productmoreimageurl3);
                                startActivity(i1);
                                }}});
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Plz.. Login for Placing Order",Toast.LENGTH_SHORT).show();
                    }


                }
            });
    }
    public List<SlideModel> checkimagestatus(List <SlideModel>imagelist,String productimageurl,String productmoreimageurl1,String productmoreimageurl2,String productmoreimageurl3)
    {
        if (productimageurl != null & productmoreimageurl1 !=null&productmoreimageurl2!=null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl1));
            imagelist.add(new SlideModel(productmoreimageurl2));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl == null & productmoreimageurl1 !=null&productmoreimageurl2!=null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productmoreimageurl1));
            imagelist.add(new SlideModel(productmoreimageurl2));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl == null & productmoreimageurl1 ==null&productmoreimageurl2!=null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productmoreimageurl2));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl == null & productmoreimageurl1 ==null&productmoreimageurl2==null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productmoreimageurl3));}

        else if (productimageurl != null & productmoreimageurl1 ==null&productmoreimageurl2!=null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl2));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl != null & productmoreimageurl1 ==null&productmoreimageurl2==null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl != null & productmoreimageurl1 ==null&productmoreimageurl2==null&productmoreimageurl3==null) {
            imagelist.add(new SlideModel(productimageurl));
       }

        else if (productimageurl != null & productmoreimageurl1 !=null&productmoreimageurl2==null&productmoreimageurl3!=null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl1));
            imagelist.add(new SlideModel(productmoreimageurl3));}
        else if (productimageurl != null & productmoreimageurl1 !=null&productmoreimageurl2==null&productmoreimageurl3==null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl1));
        }
        else if (productimageurl != null & productmoreimageurl1 !=null&productmoreimageurl2!=null&productmoreimageurl3==null) {
            imagelist.add(new SlideModel(productimageurl));
            imagelist.add(new SlideModel(productmoreimageurl1));
            imagelist.add(new SlideModel(productmoreimageurl2)); }

        else if (productimageurl != null & productmoreimageurl1 ==null&productmoreimageurl2!=null&productmoreimageurl3==null) {
            imagelist.add(new SlideModel(productimageurl));
        imagelist.add(new SlideModel(productmoreimageurl2));
        }
        else{
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
        return imagelist;
    }
}
