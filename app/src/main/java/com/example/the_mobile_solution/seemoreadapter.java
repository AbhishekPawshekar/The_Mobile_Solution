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

public class seemoreadapter extends FirestoreRecyclerAdapter<seemoreretriveclass,seemoreadapter.myholder> {
        public seemoreadapter(@NonNull FirestoreRecyclerOptions<seemoreretriveclass> options) {
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
                    Intent i=new Intent(v.getContext(),after_selecting_seemore_category.class);
                    i.putExtra("Category_Name",model.getCategory_Name());
                    v.getContext().startActivity(i);
                }
            });

        }

        @NonNull
        @Override
        public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_entity_seemorecategory,parent,false);
            return new myholder(view);
        }

        class myholder extends RecyclerView.ViewHolder {

            ImageView img;
            TextView nm;
            CardView cd;
            retrivedataclass model;
            public myholder(@NonNull View itemView) {
                super(itemView);
                nm=itemView.findViewById(R.id.seemore_category_name);
                img=itemView.findViewById(R.id.seemore_category_image);
                cd=itemView.findViewById(R.id.single_entity_seemorecategoryui);
            }
        }
    }

