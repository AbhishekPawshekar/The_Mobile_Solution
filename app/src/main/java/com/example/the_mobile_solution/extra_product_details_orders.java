
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

public class extra_product_details_orders extends AppCompatActivity {
    TextView full_name, product_all_price, product_all_discount,your_price,signupactionbar,loginactionbar,productdetails;
    ImageSlider extra_all_image;
    Button buy;
    FirebaseAuth fba;
    FirebaseUser fbu;
    FirebaseFirestore fbfs;
    FirebaseDatabase database;

    String firstname_header,lastname1_header,product_name,product_price,product_discount,saveto2,phoneno,email,productimageurl,productmoreimageurl1,productmoreimageurl2,productmoreimageurl3,pricetopay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_product_details_orders);
        Intent i = getIntent();
        product_name = i.getStringExtra("Product_Name");
        product_price = i.getStringExtra("Product_Price");
        product_discount = i.getStringExtra("Product_Discount");
        productimageurl = i.getStringExtra("Product_image_url");
        productmoreimageurl1 = i.getStringExtra("Product_More_image_url1");
        productmoreimageurl2 = i.getStringExtra("Product_More_image_url2");
        productmoreimageurl3 = i.getStringExtra("Product_More_image_url3");
        pricetopay = i.getStringExtra("Product_Price_After_discount");
        signupactionbar = findViewById(R.id.signupbar);
        loginactionbar = findViewById(R.id.loginbar);
        your_price = findViewById(R.id.displayyourpriceorder);
        full_name = findViewById(R.id.displayproductnameorder);
        product_all_price = findViewById(R.id.displayproductpriceorder);
        product_all_discount = findViewById(R.id.displayproductdiscountorder);
        extra_all_image = findViewById(R.id.extra_image_productorder);
productdetails=findViewById(R.id.displayproductdetailsorder);
//setting values to views
        full_name.setText(product_name);
        product_all_price.setText(product_price);
        product_all_discount.setText("Discount:-" + product_discount + "%");
        your_price.setText("Your Price:-" + pricetopay);
productdetails.setText(product_discount);
        List<SlideModel> imagelist = new ArrayList<>();
        extra_all_image.setImageList(checkimagestatus(imagelist, productimageurl, productmoreimageurl1, productmoreimageurl2, productmoreimageurl3), false);


        fba = FirebaseAuth.getInstance();
        fbu = fba.getCurrentUser();

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
