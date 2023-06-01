package com.example.the_mobile_solution;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class deletecategoryadapter extends FirestoreRecyclerAdapter<deletecategoryretriveclass,deletecategoryadapter .myholder> {
    public deletecategoryadapter (@NonNull FirestoreRecyclerOptions<deletecategoryretriveclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull deletecategoryadapter .myholder holder, int position, @NonNull deletecategoryretriveclass model) {
        String both=model.getCategory_Name();
        holder.nm.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
        Picasso.get().load(model.getCategory_image_url()).into(holder.img);
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Sure To Delete "+model.getCategory_Name()+" !!!..")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                                fbfs.collection("Categorys").document(model.getCategory_Name()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                                    Toast.makeText(v.getContext(), "Product Category Deleted Successfully", Toast.LENGTH_SHORT).show();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledeletecategory,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {
        int deletecounter;
        ImageView img;
        TextView nm, pri, dis;
        CardView cd;
        retrivedataclass model;

        public myholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.display_categoryimage);
            nm = itemView.findViewById(R.id.display_categoryname);
            cd = itemView.findViewById(R.id.deletecategoryui);
        }
    }}

