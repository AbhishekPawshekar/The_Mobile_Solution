package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link accountclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class accountclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public accountclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment accountclick.
     */
    // TODO: Rename and change types and number of parameters
    public static accountclick newInstance(String param1, String param2) {
        accountclick fragment = new accountclick();
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
    TextView nameaccount,useraccount,phoneaccount,emailaccount;
FirebaseAuth fba;
FirebaseUser fbu;
FirebaseFirestore fbfs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_accountclick, container, false);
        fba=FirebaseAuth.getInstance();
        fbu=fba.getCurrentUser();
        fbfs=FirebaseFirestore.getInstance();
        nameaccount=view.findViewById(R.id.enternameaccount);
        useraccount=view.findViewById(R.id.enteruseraccount);
        phoneaccount=view.findViewById(R.id.enterphoneaccount);
        emailaccount=view.findViewById(R.id.enteremailaccount);
        fbfs.collection("Customer").document(fbu.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    nameaccount.setText((String) task.getResult().get("First_Name")+" "+task.getResult().get("Last_Name"));
                    useraccount.setText((String)task.getResult().get("User_Name"));
                    phoneaccount.setText((String) task.getResult().get("Phone"));
                    emailaccount.setText((String) task.getResult().get("Email"));
                }else{
                    Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}