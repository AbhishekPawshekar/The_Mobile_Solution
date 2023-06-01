package com.example.the_mobile_solution;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminhomeclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminhomeclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminhomeclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminhomeclick.
     */
    // TODO: Rename and change types and number of parameters
    public static adminhomeclick newInstance(String param1, String param2) {
        adminhomeclick fragment = new adminhomeclick();
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
    FirebaseAuth fba;
    FirebaseUser fbu;
    FirebaseFirestore fbfs;
    RecyclerView rv;
    productdisplayuserviewadaptor myadpter;
    TextView setnoofuser,setnoofmembers,setnooforderplace,setnoofproduct,setnoofreparing;
    ImageView setadvertisement,addcategory,special_offers,deletespecialoffers,reparingorders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adminhomeclick, container, false);
        setnoofuser=view.findViewById(R.id.setnoofuser);
        setnoofmembers=view.findViewById(R.id.setnoofmembers);
        setnoofproduct=view.findViewById(R.id.setnoofproduct);
        setnooforderplace=view.findViewById(R.id.setnooforderrequest);
        setnoofreparing=view.findViewById(R.id.setnoofreparing);
        setadvertisement=view.findViewById(R.id.set_advertisement);
        addcategory=view.findViewById(R.id.add_Category);
        special_offers=view.findViewById(R.id.setspecial_offers);
        deletespecialoffers=view.findViewById(R.id.deletespecial_offers);
        reparingorders=view.findViewById(R.id.reparingorders);
        fbfs=FirebaseFirestore.getInstance();

reparingorders.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Intent i=new Intent(getContext(),display_no_of_reparing.class);
startActivity(i);
    }
});
        setadvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new addadvertisementpage()).commit();
            }
        });

        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),categorydetails.class);
                startActivity(i);
            }
        });
        special_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),select_addproduct_category_name.class);
                i.putExtra("category_name","Special_Offers");
                startActivity(i);
            }
        });
        deletespecialoffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new delete_specialoffers()).commit();
            }
        });

        fba=FirebaseAuth.getInstance();
        fbu=fba.getCurrentUser();
        FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
        fbfs.collection("Counter_Details").document("Members").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    setnoofmembers.setText((String)task.getResult().get("Total_Count"));
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });
//counting no of users
        fbfs.collection("Counter_Details").document("Users").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    setnoofuser.setText((String)task.getResult().get("Total_Count"));
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });
//counting no of product
        fbfs.collection("Counter_Details").document("Products").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    setnoofproduct.setText((String)task.getResult().get("Total_Count"));
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });

        //counting no of OrderPlaced
        fbfs.collection("Counter_Details").document("Orders").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    setnooforderplace.setText((String)task.getResult().get("Total_Count"));
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });
        fbfs.collection("Counter_Details").document("Reparing").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    setnoofreparing.setText((String)task.getResult().get("Total_Count"));
                }
                else{
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }



}