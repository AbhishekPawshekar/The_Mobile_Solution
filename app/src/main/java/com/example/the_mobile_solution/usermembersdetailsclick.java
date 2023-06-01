package com.example.the_mobile_solution;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link usermembersdetailsclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class usermembersdetailsclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public usermembersdetailsclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment usermembersdetailsclick.
     */
    // TODO: Rename and change types and number of parameters
    public static usermembersdetailsclick newInstance(String param1, String param2) {
        usermembersdetailsclick fragment = new usermembersdetailsclick();
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
TextView alluser,allmember;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_usermembersdetailsclick, container, false);
        alluser=view.findViewById(R.id.request_to_alluser);
        allmember=view.findViewById(R.id.request_to_allmembers);
        alluser.setBackgroundColor(Color.argb(255,213,146,245));

        FragmentTransaction t=getFragmentManager().beginTransaction();
        t.replace(R.id.usermemberframelayout,new alluserdetails()).commit();

        alluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allmember.setBackgroundColor(Color.argb(255,255,255,255));
                alluser.setBackgroundColor(Color.argb(255,213,146,245));
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.usermemberframelayout,new alluserdetails()).commit();

            }
        });
        allmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alluser.setBackgroundColor(Color.argb(255,255,255,255));
                allmember.setBackgroundColor(Color.argb(255,213,146,245));
                FragmentTransaction t=getFragmentManager().beginTransaction();
                t.replace(R.id.usermemberframelayout,new allmemberdetails()).commit();

            }
        });


        return view;
    }
}