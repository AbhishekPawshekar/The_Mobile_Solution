package com.example.the_mobile_solution;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.TextView;

public class categorydetails extends AppCompatActivity {
TextView addcategory,deletecategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetails);
        addcategory=findViewById(R.id.request_to_addcategory);
        deletecategory=findViewById(R.id.request_to_deletecategory);
        addcategory.setBackgroundColor(Color.argb(255,213,145,246));

     getSupportFragmentManager().beginTransaction().replace(R.id.categoryframelayout,new adminaddcategory()).commit();

        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletecategory.setBackgroundColor(Color.argb(255,255,255,255));
                addcategory.setBackgroundColor(Color.argb(255,213,145,246));
                getSupportFragmentManager().beginTransaction().replace(R.id.categoryframelayout,new adminaddcategory()).commit();

            }
        });
        deletecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcategory.setBackgroundColor(Color.argb(255,255,255,255));
                deletecategory.setBackgroundColor(Color.argb(255,213,145,246));
               getSupportFragmentManager().beginTransaction().replace(R.id.categoryframelayout,new admindeletecategory()).commit();

            }
        });
    }
}