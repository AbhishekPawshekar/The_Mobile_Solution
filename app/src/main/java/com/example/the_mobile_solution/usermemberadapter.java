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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class usermemberadapter extends FirestoreRecyclerAdapter<usermemberretriveclass,usermemberadapter.myholder> {
    public usermemberadapter(@NonNull FirestoreRecyclerOptions<usermemberretriveclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myholder holder, int position, @NonNull usermemberretriveclass model) {
        String both=model.getFirst_Name()+" "+model.getLast_Name();
holder.fn.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
holder.pn.setText(model.getPhone());
holder.em.setText(model.getEmail());
holder.un.setText(model.getUser_Name());
holder.pw.setText(model.getPassword());
holder.ad.setText(model.getAddress());
holder.cd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
        builder.setMessage("Sure To Record Of :\n "+model.getFirst_Name()+" "+model.getLast_Name())
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();

                        fbfs.collection("Customer").document(model.getEmail()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    if(model.getMember().equals("True")){
                                        DocumentReference drproduct=fbfs.collection("Counter_Details").document("Members");
                                        drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                String counter= (String) task.getResult().get("Total_Count");
                                                holder.counter=Integer.parseInt(counter);
                                            }
                                        });

                                        DocumentReference dr1=fbfs.collection("Counter_Details").document("Members");
                                    String setcounter=String.valueOf(holder.counter-1);
                                    dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(v.getContext(), "Member Deleted Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }else{ DocumentReference drproduct=fbfs.collection("Counter_Details").document("Users");
                                        drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                String counter= (String) task.getResult().get("Total_Count");
                                                holder.counter=Integer.parseInt(counter);
                                            }
                                        });

                                        DocumentReference dr1=fbfs.collection("Counter_Details").document("Users");
                                        String setcounter=String.valueOf(holder.counter-1);
                                        dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(v.getContext(), "User Deleted Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                }
                                }else{
                                    Toast.makeText(v.getContext(),"No Such User",Toast.LENGTH_SHORT).show();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleusermemberdetailsentities,parent,false);
        return new myholder(view);
    }

    class myholder extends RecyclerView.ViewHolder{
        TextView fn,pn,em,un,pw,ad;
        CardView cd;
        int counter;
int n=0;
        public myholder(@NonNull View itemview){
            super(itemview);
            fn=itemview.findViewById(R.id.entered_full_name);
            pn=itemview.findViewById(R.id.entered_phoneno);
            em=itemview.findViewById(R.id.entered_emailid);
            un=itemview.findViewById(R.id.entered_username);
            pw=itemview.findViewById(R.id.entered_password);
            ad=itemview.findViewById(R.id.entered_address);
            cd=itemview.findViewById(R.id.singleusermemberdetailsui);

        }
    }
}
