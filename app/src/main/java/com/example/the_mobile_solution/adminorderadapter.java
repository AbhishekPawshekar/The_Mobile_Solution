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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class adminorderadapter extends FirestoreRecyclerAdapter<adminorderretriveclass,adminorderadapter.holder1> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adminorderadapter(@NonNull FirestoreRecyclerOptions<adminorderretriveclass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder1 holder, int position, @NonNull adminorderretriveclass model) {
        String both=model.getFirst_Name()+" "+model.getLast_Name();
holder.firstname.setText(both.substring(0, 1).toUpperCase() + both.substring(1));
holder.phone.setText(model.getPhone());
holder.email.setText(model.getEmail());
holder.productname.setText(model.getProduct_Name());
holder.productprice.setText(model.getProduct_Price());
holder.productdiscount.setText(model.getProduct_discount());
holder.productdiscountprice.setText(model.getPrice_To_Pay());
holder.waytodelivery.setText(model.getWay_To_Delivery());
holder.deliverycharges.setText(model.getDelivery_Charges());
holder.address.setText(model.getAddress_To_Delivery());
holder.totalcharges.setText(model.getTotal_Charges());
        Picasso.get().load(model.getProduct_Image_url()).into(holder.productimage);
        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), extra_product_details_orders.class);
                        i.putExtra("Product_Name", model.getProduct_Name());
                        i.putExtra("Product_Price", model.getProduct_Price());
                        i.putExtra("Product_Discount", model.getProduct_discount());
                        i.putExtra("Product_image_url", model.getProduct_Image_url());
                        i.putExtra("Product_Price_After_discount", model.getPrice_To_Pay());
                        i.putExtra("Product_More_image_url1", model.getProduct_MoreImage_url1());
                        i.putExtra("Product_More_image_url2", model.getProduct_MoreImage_url2());
                        i.putExtra("Product_More_image_url3", model.getProduct_MoreImage_url3());
                        v.getContext().startActivity(i);

            }
        });

holder.deleteorder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
        builder.setMessage("Sure To Delete :\n"+model.getEmail()+" Order!!!..")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
                        fbfs.collection("Order").document(model.getEmail()+model.getOrder_Count()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
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
    public holder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleadminorderentities,parent,false);
        return new holder1(view);
    }

    class holder1 extends RecyclerView.ViewHolder{
        TextView firstname,email,phone,productname,productprice,productdiscount,productdiscountprice,totalcharges,address,deliverycharges,waytodelivery;
        CardView cd;
        ImageView productimage,deleteorder;
        public holder1(@NonNull View itemview){
            super(itemview);
            firstname=itemview.findViewById(R.id.adminorderfullname);
            email=itemview.findViewById(R.id.adminorderemail);
            phone=itemview.findViewById(R.id.adminorderphone);
            cd=itemview.findViewById(R.id.admindeleteorderui1);
            productname=itemview.findViewById(R.id.adminorderproductname);
            productprice=itemview.findViewById(R.id.adminorderoriginalprice);
            productdiscount=itemview.findViewById(R.id.adminorderproductdiscount);
            productdiscountprice=itemview.findViewById(R.id.adminorderdiscountprice);
            totalcharges=itemview.findViewById(R.id.adminorderoverallchargespaid);
            address=itemview.findViewById(R.id.adminorderaddress);
            deliverycharges=itemview.findViewById(R.id.adminorderdeliverycharges);
            waytodelivery=itemview.findViewById(R.id.adminorderwayofdelivery);
            productimage=itemview.findViewById(R.id.adminorderproductimage);
            deleteorder=itemview.findViewById(R.id.admindeleteorderbbutton);
        }
        }
    }
