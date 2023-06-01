package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

public class deleteproduct extends AppCompatActivity {
    TextView add,update,delete;
    delectproductadapter myadpter;
    String deleteproductcategoryname;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteproduct);
        add=findViewById(R.id.request_to_add_product);
        delete=findViewById(R.id.request_to_delete_product);
        update=findViewById(R.id.request_to_update_product);
Intent i=getIntent();
deleteproductcategoryname=i.getStringExtra("category_name1");

        rv=findViewById(R.id.deleteproducterrecyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));

        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").whereEqualTo("Category",deleteproductcategoryname), retrivedataclass.class)
                .build();
        myadpter=new delectproductadapter(options);
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
