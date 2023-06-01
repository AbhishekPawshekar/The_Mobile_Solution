package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link after_clicking_home_delivery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class after_clicking_home_delivery extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public after_clicking_home_delivery() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment after_clicking_home_delivery.
     */
    // TODO: Rename and change types and number of parameters
    public static after_clicking_home_delivery newInstance(String param1, String param2) {
        after_clicking_home_delivery fragment = new after_clicking_home_delivery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextInputLayout addresstodelivery;
    TextView totalprice,deliverycharges,productprice;
    Button order;
    int buy;
    String delivery_charges,product_name,product_details,product_price,product_discount,price_to_pay,email, first_name, last_name, phone, order_count, product_image_url, product_moreimage_url1,product_moreimage_url2,product_moreimage_url3;
    FirebaseFirestore fbfs=FirebaseFirestore.getInstance();

    public void collect_data_for_order(String Product_Name,String Product_Details,String Product_discount,String Product_Price,String Price_To_Pay,String Email,String First_Name,String Last_Name,String Phone,String Order_Count,String Product_Image_url,String Product_MoreImage_url1,String Product_MoreImage_url2,String Product_MoreImage_url3){
        product_name=Product_Name;
        product_details=Product_Details;
        product_discount=Product_discount;
        product_price=Product_Price;
        price_to_pay=Price_To_Pay;
        email=Email;
        first_name=First_Name;
        last_name=Last_Name;
        phone=Phone;
        order_count=Order_Count;
        product_image_url=Product_Image_url;
        product_moreimage_url1=Product_MoreImage_url1;
        product_moreimage_url2=Product_MoreImage_url2;
        product_moreimage_url3=Product_MoreImage_url3;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_after_clicking_home_delivery, container, false);
        order=view.findViewById(R.id.orderbuttonhome);
        productprice=view.findViewById(R.id.productpricebuyhome);
        deliverycharges=view.findViewById(R.id.deliverybuyhome);
        totalprice=view.findViewById(R.id.totalbuyhome);
        addresstodelivery=view.findViewById(R.id.address_to_delivery);
        productprice.setText(price_to_pay);
        totalprice.setText(price_to_pay);



        fbfs.collection("Product").document(product_name+product_details).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    delivery_charges=(String) task.getResult().get("Delivery_Charges");
                    buy=Integer.parseInt(price_to_pay)+Integer.parseInt(delivery_charges);
                    deliverycharges.setText(delivery_charges+" Rs");
                    totalprice.setText(String.valueOf(buy));
                }}
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference dr1=fbfs.collection("Order").document(email+order_count);
                Map<String,String> orderplaced=new HashMap<>();
                orderplaced.put("Product_Name",product_name);
                orderplaced.put("Product_Price",product_price);
                orderplaced.put("Product_discount",product_discount);
                orderplaced.put("Price_To_Pay",price_to_pay);
                orderplaced.put("Total_Charges",String.valueOf(buy));
                orderplaced.put("Email",email);
                orderplaced.put("Address_To_Delivery",addresstodelivery.getEditText().getText().toString());
                orderplaced.put("Delivery_Charges",delivery_charges);
                orderplaced.put("Way_To_Delivery","Home Delivery");
                orderplaced.put("First_Name",first_name);
                orderplaced.put("Last_Name",last_name);
                orderplaced.put("Phone",phone);
                orderplaced.put("Order_Count",order_count);
                orderplaced.put("Product_Image_url",product_image_url);
                orderplaced.put("Product_MoreImage_url1",product_moreimage_url1);
                orderplaced.put("Product_MoreImage_url2",product_moreimage_url2);
                orderplaced.put("Product_MoreImage_url3",product_moreimage_url3);
                dr1.set(orderplaced).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            DocumentReference dr1=fbfs.collection("Customer").document(email);
                            dr1.update("Order_Count",order_count).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(getContext(),"Order Placed...",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
        return view;
    }
}