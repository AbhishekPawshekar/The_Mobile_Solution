package com.example.the_mobile_solution;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class delectproductadapter extends FirestoreRecyclerAdapter<retrivedataclass,delectproductadapter.myholder> {
    public delectproductadapter(@NonNull FirestoreRecyclerOptions<retrivedataclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull retrivedataclass model) {
        String both=model.getProduct_Name()+" "+model.getProduct_Details();
        holder.nm.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
        holder.pri.setText("Rs."+model.getProduct_Price());
        holder.dis.setText(model.getProduct_Discount()+"%");
        Picasso.get().load(model.getProduct_image_url()).into(holder.img);
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                DocumentReference drproduct=fbfs.collection("Counter_Details").document("Products");
                drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String counter= (String) task.getResult().get("Total_Count");
                        holder.deletecounter=Integer.parseInt(counter);
                    }
                });

                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Sure To Delete:\n "+model.getProduct_Name()+" "+model.getProduct_Details())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                                fbfs.collection("Product").document(model.getProduct_Name()+model.getProduct_Details()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            StorageReference storageReference=FirebaseStorage.getInstance().getReference().child(model.getProduct_Name()+model.getProduct_Details()+"/"+"mainimage");
                                            storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    StorageReference storageReference=FirebaseStorage.getInstance().getReference().child(model.getProduct_Name()+model.getProduct_Details()+"/"+"moreimage1");
                                                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            StorageReference storageReference=FirebaseStorage.getInstance().getReference().child(model.getProduct_Name()+model.getProduct_Details()+"/"+"moreimage2");
                                                            storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    StorageReference storageReference=FirebaseStorage.getInstance().getReference().child(model.getProduct_Name()+model.getProduct_Details()+"/"+"moreimage3");
                                                                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            DocumentReference dr1=fbfs.collection("Counter_Details").document("Products");
                                                                            String setcounter=String.valueOf(holder.deletecounter-1);
                                                                            dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    Toast.makeText(v.getContext(), "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }
                                                                    });

                                                                }
                                                            });

                                                        }
                                                    });

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(v.getContext(),"Error:"+e,Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }else{
                                            Toast.makeText(v.getContext(),"No Such Product",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }
        });

    }
    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledeleteproductentities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {
int deletecounter;
        ImageView img;
        TextView nm,pri,dis;
        CardView cd;
        retrivedataclass model;
        public myholder(@NonNull View itemView) {
            super(itemView);
            nm=itemView.findViewById(R.id.entered_productname);
            pri=itemView.findViewById(R.id.entered_productprice);
            dis=itemView.findViewById(R.id.entered_productdiscount);
            img=itemView.findViewById(R.id.entered_productimage);
            cd=itemView.findViewById(R.id.deleteproductsingleentities);
        }
    }
}