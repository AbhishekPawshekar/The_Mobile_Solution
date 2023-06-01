package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.internal.GenericIdpActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.SearchView;

public class after_selecting_seemore_category extends AppCompatActivity {
String category_name,category_url;
RecyclerView rv;
productdisplayuserviewadaptor myadpter;
SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_selecting_seemore_category);
        Intent i=getIntent();
       category_name= i.getStringExtra("Category_Name");
        category_url=i.getStringExtra("Category_image_url");
        rv=findViewById(R.id.seemore_category_selected_retriveclass);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        searchView=findViewById(R.id.after_selecting_seemore_category_searching);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                startsearch(newText);
                return false;
            }
        });

        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").whereEqualTo("Category",category_name), retrivedataclass.class)
                .build();
        myadpter=new productdisplayuserviewadaptor(options);
        rv.setAdapter(myadpter);
    }
    private void startsearch(String newText) {
        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").orderBy("Product_Name").whereEqualTo("Category",category_name).startAt(newText).endAt(newText+"\uf8ff"), retrivedataclass.class)
                .build();
        myadpter=new productdisplayuserviewadaptor(options);
        myadpter.startListening();
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