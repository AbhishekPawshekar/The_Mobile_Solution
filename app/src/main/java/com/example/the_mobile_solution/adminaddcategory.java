package com.example.the_mobile_solution;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adminaddcategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adminaddcategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adminaddcategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adminaddcategory.
     */
    // TODO: Rename and change types and number of parameters
    public static adminaddcategory newInstance(String param1, String param2) {
        adminaddcategory fragment = new adminaddcategory();
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
    TextView signupactionbar,loginactionbar;
    Button selectcategoryimage,submitcategory;
    public static final int  PICK_IMAGE_REQUEST=1;
    Uri imagefile;
    ImageView imageView;
    String downloadurl;
    StorageReference riversRef;
    TextInputLayout category;
    String category_name;
    FirebaseFirestore fbfs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_adminaddcategory, container, false);
        signupactionbar = view.findViewById(R.id.signupbar);
        loginactionbar = view.findViewById(R.id.loginbar);
        submitcategory=view.findViewById(R.id.submitcategory);
        selectcategoryimage=view.findViewById(R.id.selectcategory);
        imageView=view.findViewById(R.id.addcategoryimage);
        category=view.findViewById(R.id.category_name);
        fbfs=FirebaseFirestore.getInstance();

        selectcategoryimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,PICK_IMAGE_REQUEST);
            }
        });

        submitcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_name=category.getEditText().getText().toString().trim();
                getreducemoreimagesize(imagefile,category_name);
                StorageReference riverRef=FirebaseStorage.getInstance().getReference().child("Categorys"+"/"+category_name);
                riverRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        category_name=category.getEditText().getText().toString().trim();
                        downloadurl = uri.toString();
                        Map<String,String> addproductinfo=new HashMap<>();
                        addproductinfo.put("Category_Name",category_name);
                        addproductinfo.put("Category_image_url",downloadurl);

                        DocumentReference dr=fbfs.collection("Categorys").document(category_name);
                        dr.set(addproductinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getContext(),"Category_Uploaded",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getContext(),"Error In Uploading",Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Error"+e,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }});
            }
        });

return view;
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imagefile=data.getData();
            imageView.setImageURI(imagefile);
        }

    }
    public void getreducemoreimagesize(Uri imageuri, String location){

        riversRef = FirebaseStorage.getInstance().getReference().child("Categorys"+"/"+location);
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
            }});

    }
    //rest all validation is done
}