package com.example.the_mobile_solution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addproduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addproduct extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addproduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addproduct.
     */
    // TODO: Rename and change types and number of parameters
    public static addproduct newInstance(String param1, String param2) {
        addproduct fragment = new addproduct();
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

String mainimage="mainimage",downloadurl,productnm1,productdetails1,productprice1,productdiscount1,deliverycharges1,productpriceformembers1;
ProgressBar pd;
    Button selectimagebutton,addproductbutton;
    ImageView imageView,imageview1,imageview2,imageview3;
    TextInputLayout productnm,productdis,productprice,productdetails,deliverycharges,productpriceformembers;
    public static final int  PICK_IMAGE_REQUEST=1,PICK_IMAGE_REQUEST1=2,PICK_IMAGE_REQUEST2=3,PICK_IMAGE_REQUEST3=4;
    StorageReference riversRef;
    Uri imagefile,imagefile1,imagefile2,imagefile3;
    String product_category_to_add;

   public void oninteraction(String category){
        product_category_to_add=category;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_addproduct, container, false);


        selectimagebutton=view.findViewById(R.id.selectimage);
pd=view.findViewById(R.id.progressBar);
imageView=view.findViewById(R.id.productimage);
productnm=view.findViewById(R.id.productname1);
deliverycharges=view.findViewById(R.id.deliverycharges);
productdis=view.findViewById(R.id.discountproduct);
productprice=view.findViewById(R.id.priceofproduct);
productdetails=view.findViewById(R.id.otherdetails);
imageview1=view.findViewById(R.id.productimage1);
imageview2=view.findViewById(R.id.productimage2);
imageview3=view.findViewById(R.id.productimage3);
addproductbutton=view.findViewById(R.id.addproductbutton);
productpriceformembers=view.findViewById(R.id.priceofproductformembers);

pd.setVisibility(View.GONE);
//image select block form mobile file
selectimagebutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,PICK_IMAGE_REQUEST);
    }
});

addproductbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!validateproductdetails()|!validateproductprice()|!validateproductname()){return;}
        pd.setVisibility(View.VISIBLE);
        //reducing size of image
        getreducemoreimagesize(imagefile,mainimage);

        //upload reduce size in firebase
        productnm1=productnm.getEditText().getText().toString();
         productdetails1=productdetails.getEditText().getText().toString();

        StorageReference riverRef=FirebaseStorage.getInstance().getReference().child(productnm1.toLowerCase()+productdetails1.toLowerCase()+"/"+mainimage);
        riverRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                downloadurl = uri.toString();
                productprice1 = productprice.getEditText().getText().toString().trim();
                productdiscount1 = productdis.getEditText().getText().toString().trim();
                deliverycharges1=deliverycharges.getEditText().getText().toString();
                productpriceformembers1=productpriceformembers.getEditText().getText().toString();
                after_clicking_next_button_form_addproductclass obj = new after_clicking_next_button_form_addproductclass();
                obj.onfragmentinteraction(productnm1, productdetails1, productprice1, productdiscount1, downloadurl,product_category_to_add,deliverycharges1,productpriceformembers1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.display_category_framelayout, obj).commit();
            }
        });
    }
});
return view;}

//getting selected image form mobile file and set it on imageView
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
          imagefile=data.getData();
            imageView.setImageURI(imagefile);
        }
        if(requestCode==PICK_IMAGE_REQUEST1&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile1=data.getData();
            imageview1.setImageURI(imagefile1);
        }
        if(requestCode==PICK_IMAGE_REQUEST2&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile2=data.getData();
            imageview2.setImageURI(imagefile2);
        }
        if(requestCode==PICK_IMAGE_REQUEST3&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile3=data.getData();
            imageview3.setImageURI(imagefile3);
        }
    }
    public void getreducemoreimagesize(Uri imageuri,String location){
        productnm1=productnm.getEditText().getText().toString();
        productdetails1=productdetails.getEditText().getText().toString();
        riversRef = FirebaseStorage.getInstance().getReference().child(productnm1.toLowerCase()+productdetails1.toLowerCase()+"/"+location);
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
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {}
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100.00*snapshot.getBytesTransferred())/snapshot.getBytesTransferred();
                pd.setProgress((int)progress);
            }});

    }
    //rest all validation is done
    private boolean validateproductname()
    {
        String productnm2 = productnm.getEditText().getText().toString().trim();
        if (productnm2.isEmpty()) {
            productnm.setError("Field Can't Be Empty");
            return false;
        } else {
            productnm.setError(null);
            productnm.setErrorEnabled(false);
            return true; }
    }
    private boolean validateproductdetails()
    {
        String productdetails2=productdetails.getEditText().getText().toString().trim();
        if (productdetails2.isEmpty()) {
            productdetails.setError("Field Can't Be Empty");
            return false;
        } else {
            productdetails.setError(null);
            productdetails.setErrorEnabled(false);
            return true; }
    }
    private boolean validateproductprice()
    {
        String productprice2=productprice.getEditText().getText().toString().trim();
        if (productprice2.isEmpty()) {
            productprice.setError("Field Can't Be Empty");
            return false;
        }else {
            productprice.setError(null);
            productprice.setErrorEnabled(false);
            return true; }
    }

}