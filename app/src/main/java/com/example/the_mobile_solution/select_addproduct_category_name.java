package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class select_addproduct_category_name extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_addproduct_category_name);
        Intent i=getIntent();
        String category_name=i.getStringExtra("category_name");
        addproduct obj = new addproduct();
        obj.oninteraction(category_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.display_category_framelayout, obj).commit();
    }
}
