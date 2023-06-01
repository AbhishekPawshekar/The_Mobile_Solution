package com.example.the_mobile_solution;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class view_more_categorys extends AppCompatActivity {
RecyclerView rv;
seemoreadapter myadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more_categorys);
       rv=findViewById(R.id.morecategoryrecyclerview);
       rv.setLayoutManager(new GridLayoutManager(this,2));

        FirestoreRecyclerOptions<seemoreretriveclass> options = new FirestoreRecyclerOptions.Builder<seemoreretriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Categorys"), seemoreretriveclass.class)
                .build();
        myadpter=new seemoreadapter(options);
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