package com.example.the_mobile_solution;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeclick#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeclick extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homeclick() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeclick.
     */
    // TODO: Rename and change types and number of parameters
    public static homeclick newInstance(String param1, String param2) {
        homeclick fragment = new homeclick();
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
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference dr=db.collection("Product").document();
    ImageView display,screengrad,pdandsd,touchpad,cover,headphone,earphone,charger,datacable,battery;
    TextView seemore;
    ImageSlider advertisement;
    TextView searchView;
    String image1,image2,image3,image4,startsearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_homeclick, container, false);
        rv=view.findViewById(R.id.special_offerrecyclerview);
        rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
display=view.findViewById(R.id.mobiledisplayhomepage);
screengrad=view.findViewById(R.id.screen_gardhomepage);
pdandsd=view.findViewById(R.id.pdandsdhomepage);
touchpad=view.findViewById(R.id.mobile_touchpadhomepage);
cover=view.findViewById(R.id.mobile_coverhomepage);
headphone=view.findViewById(R.id.headphonehomepage);
earphone=view.findViewById(R.id.earphonehomepage);
charger=view.findViewById(R.id.mobile_chargerhomepage);
datacable=view.findViewById(R.id.mobiledata_cabelhomepage);
battery=view.findViewById(R.id.mobile_batterhomepage);
advertisement=view.findViewById(R.id.advertisementimageslider);
seemore=view.findViewById(R.id.category_seemore);
searchView=view.findViewById(R.id.searchview);
searchView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(getContext(),startsearching.class);
        startActivity(i);
    }
});

        StorageReference sr= FirebaseStorage.getInstance().getReference().child("Advertisement/"+"advertisement1");
        sr.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                image1=uri.toString();
                StorageReference sr1= FirebaseStorage.getInstance().getReference().child("Advertisement/"+"advertisement2");
                sr1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        image2=uri.toString();
                        StorageReference sr2= FirebaseStorage.getInstance().getReference().child("Advertisement/"+"advertisement3");
                        sr2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                image3=uri.toString();
                                
                                StorageReference sr3= FirebaseStorage.getInstance().getReference().child("Advertisement/"+"advertisement4");
                                sr3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        image4=uri.toString();
                                        List<SlideModel> imagelist = new ArrayList<>();
                                        advertisement.setImageList(checkimagestatus(imagelist,image1,image2,image3,image4), false);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),view_more_categorys.class);
                startActivity(i);
            }
        });

        FirestoreRecyclerOptions<retrivedataclass> options = new FirestoreRecyclerOptions.Builder<retrivedataclass>()
                .setQuery(FirebaseFirestore.getInstance().collection("Product").whereEqualTo("Category","Special_Offers"), retrivedataclass.class)
                .build();
        myadpter=new productdisplayuserviewadaptor(options);
        rv.setAdapter(myadpter);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new mobiledisplay_category_selected()).commit();
            }
        });
        screengrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new screengrad_category_selected()).commit();
            }
        });
        pdandsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new pdandsd_category_select()).commit();
            }
        });
        touchpad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new touchpad_category_selected()).commit();
            }
        });
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new cover_category_selected()).commit();
            }
        });
        headphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new headphone_category_selected()).commit();
            }
        });
        earphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new earphone_category_select()).commit();
            }
        });
        charger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new charger_category_select()).commit();
            }
        });
        datacable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new datacabel_category_select()).commit();
            }
        });
        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new battery_category_select()).commit();
            }
        });

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
    public List<SlideModel> checkimagestatus(List <SlideModel>imagelist,String image1,String image2,String image3,String image4)
    {
        if (image1 != null & image2 !=null&image3!=null&image4!=null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image2));
            imagelist.add(new SlideModel(image3));
            imagelist.add(new SlideModel(image4));}
        else if (image1 == null & image2 !=null&image3!=null&image4!=null) {
            imagelist.add(new SlideModel(image2));
            imagelist.add(new SlideModel(image3));
            imagelist.add(new SlideModel(image4));}
        else if (image1 == null & image2 ==null&image3!=null&image4!=null) {
            imagelist.add(new SlideModel(image3));
            imagelist.add(new SlideModel(image4));}
        else if (image1 == null & image2 ==null&image3==null&image4!=null) {
            imagelist.add(new SlideModel(image4));}

        else if (image1 != null & image2 ==null&image3!=null&image4!=null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image3));
            imagelist.add(new SlideModel(image4));}
        else if (image1 != null & image2 ==null&image3==null&image4!=null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image4));}
        else if (image1 != null & image2 ==null&image3==null&image4==null) {
            imagelist.add(new SlideModel(image1));
        }
        else if (image1 != null & image2 !=null&image3==null&image4!=null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image2));
            imagelist.add(new SlideModel(image4));}
        else if (image1 != null & image2 !=null&image3==null&image4==null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image2));
        }
        else if (image1 != null & image2 !=null&image3!=null&image4==null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image2));
            imagelist.add(new SlideModel(image3)); }

        else if (image1 != null & image2 ==null&image3!=null&image4==null) {
            imagelist.add(new SlideModel(image1));
            imagelist.add(new SlideModel(image3));
        }
        else{
            Toast.makeText(getActivity(),"Error in uploading advertisement",Toast.LENGTH_SHORT).show();
        }
        return imagelist;
    }



}