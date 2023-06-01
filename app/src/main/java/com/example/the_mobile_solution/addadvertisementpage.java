package com.example.the_mobile_solution;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addadvertisementpage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addadvertisementpage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addadvertisementpage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addadvertisementpage.
     */
    // TODO: Rename and change types and number of parameters
    public static addadvertisementpage newInstance(String param1, String param2) {
        addadvertisementpage fragment = new addadvertisementpage();
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
    String moreadvertisement1="advertisement1",moreadvertisement2="advertisement2",moreadvertisement3="advertisement3",moreadvertisment4="advertisement4";
    StorageReference riversRef;
    Uri imagefile1,imagefile0,imagefile2,imagefile3;
Button addad1,addad2,addad3,addad4,submitad;
    ImageView iv1,iv2,iv3,iv4;
    public static final int  PICK_IMAGE_REQUEST0=1,PICK_IMAGE_REQUEST1=2,PICK_IMAGE_REQUEST2=3,PICK_IMAGE_REQUEST3=4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_addadvertisementpage, container, false);
        addad1=view.findViewById(R.id.addadvertisement1button);
        addad2=view.findViewById(R.id.addadvertisement2button);
        addad3=view.findViewById(R.id.addadvertisement3button);
        addad4=view.findViewById(R.id.addadvertisement4button);
        iv1=view.findViewById(R.id.addadvertisement1);
        iv2=view.findViewById(R.id.addadvertisement2);
        iv3=view.findViewById(R.id.addadvertisement3);
        iv4=view.findViewById(R.id.addadvertisement4);
        submitad=view.findViewById(R.id.submitadvertisementbutton);

        addad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST0);
            }
        });
        addad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST1);
            }
        });
        addad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST2);
            }
        });
        addad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST3);
            }
        });
submitad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        getreducemoreimagesize(imagefile0,moreadvertisement1);
        getreducemoreimagesize(imagefile1,moreadvertisement2);
        getreducemoreimagesize(imagefile2,moreadvertisement3);
        getreducemoreimagesize(imagefile3,moreadvertisment4);

    }
});

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST0&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile0=data.getData();
            iv1.setImageURI(imagefile0);
        }
        if(requestCode==PICK_IMAGE_REQUEST1&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile1=data.getData();
            iv2.setImageURI(imagefile1);
        }
        if(requestCode==PICK_IMAGE_REQUEST2&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile2=data.getData();
            iv3.setImageURI(imagefile2);
        }
        if(requestCode==PICK_IMAGE_REQUEST3&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile3=data.getData();
            iv4.setImageURI(imagefile3);
        }
    }

    public void getreducemoreimagesize(Uri imageuri, String location){

        riversRef = FirebaseStorage.getInstance().getReference().child("Advertisement/" + location);
        Bitmap fullsizebitmap=null;

        try {
            fullsizebitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageuri);
        }catch (IOException e)
        {e.printStackTrace(); }

        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        fullsizebitmap.compress(Bitmap.CompressFormat.JPEG,25,bos);
        byte[] bitmapdata=bos.toByteArray();
        UploadTask uploadTask=riversRef.putBytes(bitmapdata);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(),"Advertisement image uploaded safly",Toast.LENGTH_SHORT).show();
            }
        });

    }
}