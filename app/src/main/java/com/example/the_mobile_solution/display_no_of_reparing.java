package com.example.the_mobile_solution;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class display_no_of_reparing extends AppCompatActivity {
    deletereparingordersadapter myadpter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_no_of_reparing);
        rv=findViewById(R.id.reparingrecyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<retrivereparingclass> options = new FirestoreRecyclerOptions.Builder<retrivereparingclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Request_To_Reparing"), retrivereparingclass.class)
                .build();
        myadpter=new deletereparingordersadapter(options);
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
