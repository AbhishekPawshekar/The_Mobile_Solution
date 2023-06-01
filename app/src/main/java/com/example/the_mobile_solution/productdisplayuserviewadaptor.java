package com.example.the_mobile_solution;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.io.File;

public class productdisplayuserviewadaptor extends FirestoreRecyclerAdapter<retrivedataclass,productdisplayuserviewadaptor.myholder> {
    public productdisplayuserviewadaptor(@NonNull FirestoreRecyclerOptions<retrivedataclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull retrivedataclass model) {
String both=model.getProduct_Name();
        holder.nm.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
        holder.pri.setText("Rs."+model.getProduct_Price());
        holder.dis.setText("Discount:"+model.getProduct_Discount()+"%");
        Picasso.get().load(model.getProduct_image_url()).into(holder.img);
        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
        FirebaseAuth fba=FirebaseAuth.getInstance();
        FirebaseUser fbu=fba.getCurrentUser();
        if(fbu!=null){
        fbfs.collection("Customer").document(fbu.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    String member=(String) task.getResult().get("Member");
                    if(member.equals("True")){
                        holder.pri.setText("Rs."+model.getProduct_Price_For_Members());
                        holder.cd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i=new Intent(v.getContext(),extra_product_details.class);
                                i.putExtra("Product_Name",model.getProduct_Name());
                                i.putExtra("Product_Details",model.getProduct_Details());
                                i.putExtra("Product_Price",model.getProduct_Price_For_Members());
                                i.putExtra("Product_Discount",model.getProduct_Discount());
                                i.putExtra("Product_image_url",model.getProduct_image_url());
                                i.putExtra("Product_More_image_url1",model.getProduct_More_image_url1());
                                i.putExtra("Product_More_image_url2",model.getProduct_More_image_url2());
                                i.putExtra("Product_More_image_url3",model.getProduct_More_image_url3());
                                v.getContext().startActivity(i);
                            }
                        });

                    }else{
                        holder.cd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i=new Intent(v.getContext(),extra_product_details.class);
                                i.putExtra("Product_Name",model.getProduct_Name());
                                i.putExtra("Product_Details",model.getProduct_Details());
                                i.putExtra("Product_Price",model.getProduct_Price());
                                i.putExtra("Product_Discount",model.getProduct_Discount());
                                i.putExtra("Product_image_url",model.getProduct_image_url());
                                i.putExtra("Product_More_image_url1",model.getProduct_More_image_url1());
                                i.putExtra("Product_More_image_url2",model.getProduct_More_image_url2());
                                i.putExtra("Product_More_image_url3",model.getProduct_More_image_url3());
                                v.getContext().startActivity(i);
                            }
                        });
                    }
                }
            }
        });
        }

        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),extra_product_details.class);
                i.putExtra("Product_Name",model.getProduct_Name());
                i.putExtra("Product_Details",model.getProduct_Details());
                i.putExtra("Product_Price",model.getProduct_Price());
                i.putExtra("Product_Discount",model.getProduct_Discount());
                i.putExtra("Product_image_url",model.getProduct_image_url());
                i.putExtra("Product_More_image_url1",model.getProduct_More_image_url1());
                i.putExtra("Product_More_image_url2",model.getProduct_More_image_url2());
                i.putExtra("Product_More_image_url3",model.getProduct_More_image_url3());
                v.getContext().startActivity(i);
            }
        });

    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleproductdisplayuserview,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView nm,pri,dis;
        CardView cd;
        retrivedataclass model;
        public myholder(@NonNull View itemView) {
            super(itemView);
            nm=itemView.findViewById(R.id.allproductname);
            pri=itemView.findViewById(R.id.allproductprice);
            dis=itemView.findViewById(R.id.allproductdiscount);
            img=itemView.findViewById(R.id.alldisplayimage);
            cd=itemView.findViewById(R.id.singleproductdisplayuserviewui);
        }
    }
}
