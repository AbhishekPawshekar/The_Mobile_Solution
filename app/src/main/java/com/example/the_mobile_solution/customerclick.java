package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link customerclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class customerclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public customerclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment customerclick.
     */
    // TODO: Rename and change types and number of parameters
    public static customerclick newInstance(String param1, String param2) {
        customerclick fragment = new customerclick();
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
    delete_reparing_order_name_adapter myadpter;
    RecyclerView rv;
    TextView add,update,delete;
    ImageView mobiledisplay,mobilebatter,mobileheadphone,mobileearphone,mobilecover,screengrad,pdandsd,mobiletouchpad,mobilecharger,mobilecable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_customerclick, container, false);
        mobiledisplay=view.findViewById(R.id.mobile_display);
        mobilebatter=view.findViewById(R.id.mobile_batterselect);
        mobilecable=view.findViewById(R.id.mobiledata_cabel);
        mobileheadphone=view.findViewById(R.id.headphone);
        mobilecharger=view.findViewById(R.id.mobile_charger);
        mobileearphone=view.findViewById(R.id.earphone);
        mobilecover=view.findViewById(R.id.mobile_cover);
        screengrad=view.findViewById(R.id.screen_gard);
        pdandsd=view.findViewById(R.id.pdandsd);
        mobiletouchpad=view.findViewById(R.id.mobile_touchpad);
        rv=view.findViewById(R.id.add_see_more_recyclerview);
        rv.setLayoutManager(new GridLayoutManager(getContext(),4));
        mobiledisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Display");
                startActivity(i);

            }
        });

        mobilebatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Battery");
                startActivity(i);

            }
        });

        mobilecable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Datacable");
                startActivity(i);
            }
        });

        mobileheadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Headphone");
                startActivity(i);

            }
        });

        mobilecharger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Charger");
                startActivity(i);
            }
        });

        mobileearphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Earphone");
                startActivity(i);
            }
        });

        mobilecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Cover");
                startActivity(i);
            }
        });

        screengrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Screengrad");
                startActivity(i);
            }
        });

        pdandsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","PD_SD");
                startActivity(i);
            }
        });

        mobiletouchpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),request_to_reparing.class);
                i.putExtra("category_name","Mobile_Touchpad");
                startActivity(i);
            }
        });

        FirestoreRecyclerOptions<seemoreretriveclass> options = new FirestoreRecyclerOptions.Builder<seemoreretriveclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Categorys"), seemoreretriveclass.class)
                .build();
        myadpter=new delete_reparing_order_name_adapter(options);
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
    }

}