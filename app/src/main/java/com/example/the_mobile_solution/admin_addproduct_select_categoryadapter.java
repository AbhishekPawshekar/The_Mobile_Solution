package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class admin_addproduct_select_categoryadapter extends FirestoreRecyclerAdapter<seemoreretriveclass,admin_addproduct_select_categoryadapter.myholder> {
        public admin_addproduct_select_categoryadapter(@NonNull FirestoreRecyclerOptions<seemoreretriveclass> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull seemoreretriveclass model) {
String both=model.getCategory_Name();
            holder.nm.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
            Picasso.get().load(model.getCategory_image_url()).into(holder.img);

            holder.cd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Intent i=new Intent(v.getContext(),select_addproduct_category_name.class);
                 i.putExtra("category_name",model.getCategory_Name());

                 v.getContext().startActivity(i);

                    //obj.oninteraction(model.getCategory_Name());

                }
            });

        }

        @NonNull
        @Override
        public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_entity_admin_addproduct_select_category,parent,false);
            return new myholder(view);
        }

        class myholder extends RecyclerView.ViewHolder {

            ImageView img;
            TextView nm;
            CardView cd;
            retrivedataclass model;
            public myholder(@NonNull View itemView) {
                super(itemView);
                nm=itemView.findViewById(R.id.admin_category_select_text);
                img=itemView.findViewById(R.id.admin_category_select_image);
                cd=itemView.findViewById(R.id.single_entity_adminproduct_select_categoryui);
            }
        }
    }


