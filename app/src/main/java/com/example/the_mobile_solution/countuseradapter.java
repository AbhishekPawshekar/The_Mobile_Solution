package com.example.the_mobile_solution;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class countuseradapter extends FirestoreRecyclerAdapter<retrivedataclass,countuseradapter.myholder> {
    public countuseradapter(@NonNull FirestoreRecyclerOptions<retrivedataclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull retrivedataclass model) {
    }


    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleproductdisplayuserview,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {
int count=0;

        CardView cd;
        public myholder(@NonNull View itemView) {
            super(itemView);
            cd=itemView.findViewById(R.id.singleproductdisplayuserviewui);
            Intent i=new Intent(itemView.getContext(),extra_product_details.class);
            i.putExtra("total number of user",count+=1);
            itemView.getContext().startActivity(i);
        }
    }
}
