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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link after_clicking_next_button_form_addproductclass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class after_clicking_next_button_form_addproductclass extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public after_clicking_next_button_form_addproductclass() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment after_clicking_next_button_form_addproductclass.
     */
    // TODO: Rename and change types and number of parameters
    public static after_clicking_next_button_form_addproductclass newInstance(String param1, String param2) {
        after_clicking_next_button_form_addproductclass fragment = new after_clicking_next_button_form_addproductclass();
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
    String moreimage1="moreimage1",moreimage2="moreimage2",moreimage3="moreimage3",mainimage="mainimage",downloadurl,downloadurl1,downloadurl2,downloadurl3,product_category_to_add;
    Button selectimage1,selectimage2,selectimage3,addmoreimagebutton;
    StorageReference riversRef;
    FirebaseAuth fba=FirebaseAuth.getInstance();
    FirebaseUser fbu;
    int productcounter;
    ImageView productimage1,productimage2,productimage3;
    Uri imagefile1,imagefile2,imagefile3;
    FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    String productnm,productdetails,productprice,productdiscount,deliverycharges,productpriceformembers;
    public static final int  PICK_IMAGE_REQUEST1=2,PICK_IMAGE_REQUEST2=3,PICK_IMAGE_REQUEST3=4;


    public void onfragmentinteraction(String productnm1,String productdetails1,String productprice1,String productdiscount1,String downloadurl1,String product_category,String delivery,String productpriceformembers1){
        productnm=productnm1;
        productdetails=productdetails1;
        productprice=productprice1;
        productdiscount=productdiscount1;
        downloadurl=downloadurl1;
        product_category_to_add=product_category;
        deliverycharges=delivery;
    productpriceformembers=productpriceformembers1;}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_after_clicking_next_button_form_addproductclass, container, false);
        selectimage1=view.findViewById(R.id.selectimage1);
        selectimage2=view.findViewById(R.id.selectimage2);
        selectimage3=view.findViewById(R.id.selectimage3);
        productimage1=view.findViewById(R.id.productimage1);
        productimage2=view.findViewById(R.id.productimage2);
        productimage3=view.findViewById(R.id.productimage3);
        addmoreimagebutton=view.findViewById(R.id.addmoreimagebutton);
        fbfs=FirebaseFirestore.getInstance();
        fba=FirebaseAuth.getInstance();
fbu=fba.getCurrentUser();
selectimage2.setEnabled(false);
selectimage3.setEnabled(false);
        selectimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST1);
            }
        });
        selectimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST2);
            }
        });
        selectimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST3);
            }
        });

addmoreimagebutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        DocumentReference drproduct=fbfs.collection("Counter_Details").document("Products");
        drproduct.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String counter= (String) task.getResult().get("Total_Count");
                productcounter=Integer.parseInt(counter);
            }
        });

        getreducemoreimagesize(imagefile1,moreimage1);
        getreducemoreimagesize(imagefile2,moreimage2);
        getreducemoreimagesize(imagefile3,moreimage3);


Map<String,String> obj=new HashMap<>();
        obj.put("Product_Name",productnm.toLowerCase());
        obj.put("Product_Details",productdetails.toLowerCase());
        obj.put("Product_Price",productprice);
        obj.put("Product_Discount",productdiscount);
        obj.put("Category",product_category_to_add);
        obj.put("Product_image_url",downloadurl);
        obj.put("Delivery_Charges",deliverycharges);
        obj.put("Product_Price_For_Members",productpriceformembers);



        //adding product to database
        DocumentReference dr=fbfs.collection("Product").document(productnm.toLowerCase()+productdetails.toLowerCase());
                                dr.set(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            StorageReference riverRef1=FirebaseStorage.getInstance().getReference().child(productnm.toLowerCase()+productdetails.toLowerCase()+"/"+moreimage1);
                                            riverRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                public void onSuccess(Uri uri)
                                                {
                                                    downloadurl1=uri.toString();
                                                    DocumentReference dr=fbfs.collection("Product").document(productnm.toLowerCase()+productdetails.toLowerCase());
                                                    dr.update("Product_More_image_url1",downloadurl1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(),"Image 1 added",Toast.LENGTH_SHORT).show();


                                            StorageReference riverRef2=FirebaseStorage.getInstance().getReference().child(productnm.toLowerCase()+productdetails.toLowerCase()+"/"+moreimage2);
                                            riverRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                public void onSuccess(Uri uri)
                                                {
                                                    downloadurl2=uri.toString();
                                                    DocumentReference dr=fbfs.collection("Product").document(productnm.toLowerCase()+productdetails.toLowerCase());
                                                    dr.update("Product_More_image_url2",downloadurl2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(),"Image 2 added",Toast.LENGTH_SHORT).show();

                                            StorageReference riverRef3=FirebaseStorage.getInstance().getReference().child(productnm.toLowerCase()+productdetails.toLowerCase()+"/"+moreimage3);
                                            riverRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                public void onSuccess(Uri uri)
                                                {
                                                    downloadurl3=uri.toString();
                                                    DocumentReference dr=fbfs.collection("Product").document(productnm.toLowerCase()+productdetails.toLowerCase());
                                                    dr.update("Product_More_image_url3",downloadurl3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getContext(),"Image 3 added",Toast.LENGTH_SHORT).show();


                                            //updating counter
                                            DocumentReference dr1=fbfs.collection("Counter_Details").document("Products");
                                            String setcounter=String.valueOf(productcounter+1);
                                            dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(getActivity(),"Product Uploaded Successfully",Toast.LENGTH_SHORT).show();
                                                    Intent i=new Intent(getContext(),admin_view.class);
                                                    getActivity().startActivity(i);
                                                }
                                            });
                                                        }
                                                    });
                                                }}
                                            );
                                                        }
                                                    });
                                                }}
                                            );
                                                        }
                                                    });
                                                }}
                                            );
                                            


                                    }else{
                                            Toast.makeText(getActivity(),"Error In Uploading",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getActivity(),"Error"+e,Toast.LENGTH_SHORT).show();
                                    }
                                });

    }
});

    return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST1&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile1=data.getData();
            productimage1.setImageURI(imagefile1);
            selectimage2.setEnabled(true);
            //reducing size of image

        }
        if(requestCode==PICK_IMAGE_REQUEST2&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile2=data.getData();
            productimage2.setImageURI(imagefile2);
            selectimage3.setEnabled(true);
        }
        if(requestCode==PICK_IMAGE_REQUEST3&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile3=data.getData();
            productimage3.setImageURI(imagefile3);

        }
    }

    public void getreducemoreimagesize(Uri imageuri, String location){
//code to reduce size and add to firebase storage
        riversRef = FirebaseStorage.getInstance().getReference().child(productnm.toLowerCase()+productdetails.toLowerCase()+"/"+location);
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
        });

    }
}