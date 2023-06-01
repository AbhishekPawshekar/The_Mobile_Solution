package com.example.the_mobile_solution;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class userorderadapter extends FirestoreRecyclerAdapter<userorderretriveclass,userorderadapter.holder1> {
    public userorderadapter(@NonNull FirestoreRecyclerOptions<userorderretriveclass> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull holder1 holder, int position, @NonNull userorderretriveclass model) {
        String both=model.getProduct_Name();
        holder.productname.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
       Picasso.get().load(model.getProduct_Image_url()).into(holder.iv);
        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_product_details_orders.class);
                i.putExtra("Product_Name",model.getProduct_Name());
                i.putExtra("Product_Price",model.getProduct_Price());
                i.putExtra("Product_Discount",model.getProduct_discount());
                i.putExtra("Product_image_url",model.getProduct_Image_url());
                i.putExtra("Product_Price_After_discount",model.getPrice_To_Pay());
                i.putExtra("Product_More_image_url1",model.getProduct_MoreImage_url1());
                i.putExtra("Product_More_image_url2",model.getProduct_MoreImage_url2());
                i.putExtra("Product_More_image_url3",model.getProduct_MoreImage_url3());
                v.getContext().startActivity(i);
            }
        });
    }


    @NonNull
    @Override
    public holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledeletecategory,parent,false);
        return new holder1(view);
    }

    class holder1 extends RecyclerView.ViewHolder{
        TextView productname;
        ImageView iv;
        CardView cd;

        public holder1(@NonNull View itemview){
            super(itemview);
            productname=itemview.findViewById(R.id.display_categoryname);
           iv=itemview.findViewById(R.id.display_categoryimage);
             cd = itemView.findViewById(R.id.deletecategoryui);
        }
    }
}