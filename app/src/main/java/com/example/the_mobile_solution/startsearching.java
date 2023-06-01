package com.example.the_mobile_solution;

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
import android.widget.SearchView;

public class startsearching extends AppCompatActivity {
    RecyclerView rv;
    retrivedataadapter myadpter;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startsearching);
     searchView=findViewById(R.id.texttosearch);
     rv=findViewById(R.id.searchingrecyclerview);
     rv.setLayoutManager(new LinearLayoutManager(this));
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
    }
    private void startsearch(String newText) {
        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").orderBy("Product_Name").startAt(newText).endAt(newText+"\uf8ff"), retrivedataclass.class)
                .build();
        myadpter=new retrivedataadapter(options);
        myadpter.startListening();
        rv.setAdapter(myadpter);

    }

}