package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admindeletecategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admindeletecategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public admindeletecategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admindeletecategory.
     */
    // TODO: Rename and change types and number of parameters
    public static admindeletecategory newInstance(String param1, String param2) {
        admindeletecategory fragment = new admindeletecategory();
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
    deletecategoryadapter myadpter;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admindeletecategory, container, false);
        rv=view.findViewById(R.id.deletecategoryrecyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FirestoreRecyclerOptions<deletecategoryretriveclass> options = new FirestoreRecyclerOptions.Builder<deletecategoryretriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Categorys"), deletecategoryretriveclass.class)
                .build();
        myadpter=new deletecategoryadapter(options);
        rv.setAdapter(myadpter);
        return  view;
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
    }
}