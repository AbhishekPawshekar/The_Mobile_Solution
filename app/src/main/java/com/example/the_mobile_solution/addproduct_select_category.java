package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class addproduct_select_category extends AppCompatActivity {
    admin_addproduct_select_categoryadapter myadpter;
    RecyclerView rv;
    TextView add,update,delete;
    ImageView mobiledisplay,mobilebatter,mobileheadphone,mobileearphone,mobilecover,screengrad,pdandsd,mobiletouchpad,mobilecharger,mobilecable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct_select_category);
        mobiledisplay=findViewById(R.id.mobile_display);
        mobilebatter=findViewById(R.id.mobile_batterselect);
        mobilecable=findViewById(R.id.mobiledata_cabel);
        mobileheadphone=findViewById(R.id.headphone);
        mobilecharger=findViewById(R.id.mobile_charger);
        mobileearphone=findViewById(R.id.earphone);
        mobilecover=findViewById(R.id.mobile_cover);
        screengrad=findViewById(R.id.screen_gard);
        pdandsd=findViewById(R.id.pdandsd);
        mobiletouchpad=findViewById(R.id.mobile_touchpad);
add=findViewById(R.id.request_to_add_product);
delete=findViewById(R.id.request_to_delete_product);
update=findViewById(R.id.request_to_update_product);
        rv=findViewById(R.id.add_see_more_recyclerview);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        mobiledisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Display");
                startActivity(i);

            }
        });

        mobilebatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Battery");
                startActivity(i);

            }
        });

        mobilecable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Datacable");
                startActivity(i);
            }
        });

        mobileheadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Headphone");
                startActivity(i);

            }
        });

        mobilecharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Charger");
                startActivity(i);
            }
        });

        mobileearphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Earphone");
                startActivity(i);
            }
        });

        mobilecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Cover");
                startActivity(i);
            }
        });

        screengrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Screengrad");
                startActivity(i);
            }
        });

        pdandsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","PD_SD");
                startActivity(i);
            }
        });

        mobiletouchpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Mobile_Touchpad");
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),addproduct_select_category.class);
                startActivity(i);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),updateproduct.class);
                startActivity(i);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),deleteproduct_select_category.class);
                startActivity(i);
            }
        });


        FirestoreRecyclerOptions<seemoreretriveclass> options = new FirestoreRecyclerOptions.Builder<seemoreretriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Categorys"), seemoreretriveclass.class)
                .build();
        myadpter=new admin_addproduct_select_categoryadapter(options);
        rv.setAdapter(myadpter);
    }
    @Override
    public void onStart() {
        super.onStart();
        myadpter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadpter.stopListening();
    }
}



