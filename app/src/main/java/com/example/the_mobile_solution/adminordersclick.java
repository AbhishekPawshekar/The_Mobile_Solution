package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminordersclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminordersclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminordersclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminordersclick.
     */
    // TODO: Rename and change types and number of parameters
    public static adminordersclick newInstance(String param1, String param2) {
        adminordersclick fragment = new adminordersclick();
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

    RecyclerView rv;
    adminorderadapter myadpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adminordersclick, container, false);
        rv = view.findViewById(R.id.recycleradminorder);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FirestoreRecyclerOptions<adminorderretriveclass> options = new FirestoreRecyclerOptions.Builder<adminorderretriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Order"), adminorderretriveclass.class)
                .build();

        myadpter = new adminorderadapter(options);
        rv.setAdapter(myadpter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myadpter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myadpter.stopListening();

}}