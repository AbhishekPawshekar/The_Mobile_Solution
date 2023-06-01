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

public class deletereparingordersadapter extends FirestoreRecyclerAdapter<retrivereparingclass,deletereparingordersadapter.myholder> {
    public deletereparingordersadapter(@NonNull FirestoreRecyclerOptions<retrivereparingclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull deletereparingordersadapter.myholder holder, int position, @NonNull retrivereparingclass model) {
        String both=model.getProduct_Name();
        holder.nm.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
        holder.com.setText(model.getCompany_Name());
        holder.pro.setText(model.getProblem());

        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                DocumentReference drproduct=fbfs.collection("Counter_Details").document("Reparing");
                drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        String counter= (String) task.getResult().get("Total_Count");
                        holder.reparingcounter=Integer.parseInt(counter);
                    }
                });

                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setMessage("Sure To Delete Order "+model.getProduct_Name())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                                fbfs.collection("Request_To_Reparing").document(model.getProduct_Name()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            DocumentReference dr1=fbfs.collection("Counter_Details").document("Reparing");
                                            String setcounter=String.valueOf(holder.reparingcounter-1);
                                            dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(v.getContext(), "Product Deleted Successfully", Toast.LENGTH_SHORT).show();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singledeletereparingorderentities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder {
        int reparingcounter;
        ImageView img;
        TextView nm,com,pro;
        CardView cd;
        retrivedataclass model;
        public myholder(@NonNull View itemView) {
            super(itemView);
            nm=itemView.findViewById(R.id.setdisplay_productnamereparing);
            com=itemView.findViewById(R.id.setdisplay_companynamereparing);
            pro=itemView.findViewById(R.id.setdisplayproblem);
            cd=itemView.findViewById(R.id.reparingordersui);
        }
    }}

