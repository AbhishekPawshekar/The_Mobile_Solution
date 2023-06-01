package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pdandsd_category_select#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pdandsd_category_select extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pdandsd_category_select() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pdandsd_category_select.
     */
    // TODO: Rename and change types and number of parameters
    public static pdandsd_category_select newInstance(String param1, String param2) {
        pdandsd_category_select fragment = new pdandsd_category_select();
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
    productdisplayuserviewadaptor myadpter;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pdandsd_category_select, container, false);
        rv=view.findViewById(R.id.pdandsdcategoryselectrecyclerview);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        searchView=view.findViewById(R.id.pdandsd_category_select_searching);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                startsearch(newText);
                return false;
            }
        });
        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").whereEqualTo("Category","PD_SD"), retrivedataclass.class)
                .build();
        myadpter=new productdisplayuserviewadaptor(options);
        rv.setAdapter(myadpter);
        return view;

    }
    private void startsearch(String newText) {
        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").orderBy("Product_Name").whereEqualTo("Category","PD_SD").startAt(newText).endAt(newText+"\uf8ff"), retrivedataclass.class)
                .build();
        myadpter=new productdisplayuserviewadaptor(options);
        myadpter.startListening();
        rv.setAdapter(myadpter);
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