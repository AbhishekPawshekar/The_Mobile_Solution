package com.example.the_mobile_solution;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class request_to_reparing extends AppCompatActivity {
TextInputLayout productname,companyname,problem,email,phone,fullname;
String pd,cn,pro,email1,phone1,fullname1;
Button submitrequest;
FirebaseFirestore fbfs=FirebaseFirestore.getInstance();
    int reparingcounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_to_reparing);
        Intent i=getIntent();
        String category_name=i.getStringExtra("category_name");
        productname=findViewById(R.id.nameofproduct);
        companyname=findViewById(R.id.nameofcompany);
        problem=findViewById(R.id.problem);
        submitrequest=findViewById(R.id.submitrequest);
        email=findViewById(R.id.emailofrequest);
        phone=findViewById(R.id.requestphoneno);
        fullname=findViewById(R.id.requestfullname);
        submitrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        if(!validateproductname()| !validatecompany()|!validateproblem())
        {return;}

        pd=productname.getEditText().getText().toString();
        cn=companyname.getEditText().getText().toString();
        pro=problem.getEditText().getText().toString();

        email1=email.getEditText().getText().toString();
        phone1=phone.getEditText().getText().toString();
        fullname1=fullname.getEditText().getText().toString();
        Map <String,String> obj=new HashMap();
        obj.put("Product_Name",pd);
        obj.put("Company_Name",cn);
        obj.put("Problem",pro);
        obj.put("Category_Name",category_name);
        obj.put("Full_Name",fullname1);
        obj.put("Email",email1);
        obj.put("Phone_No",phone1);

        DocumentReference drmembers=fbfs.collection("Counter_Details").document("Reparing");
        drmembers.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                String counter= (String) task.getResult().get("Total_Count");
                reparingcounter=Integer.parseInt(counter);
            }
        });

        fbfs.collection("Request_To_Reparing").document(email1).set(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    DocumentReference dr1=fbfs.collection("Counter_Details").document("Reparing");
                    String setcounter=String.valueOf(reparingcounter+1);
                    dr1.update("Total_Count",setcounter).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    Toast.makeText(getApplicationContext(),"Request For Reparing Send.",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error:"+e,Toast.LENGTH_SHORT).show();
            }
        });
            }
        });
    }
    private boolean validateproductname()
    {
        String first = productname.getEditText().getText().toString().trim();
        if (first.isEmpty()) {
            productname.setError("Field Can't Be Empty");
            return false;
        } else if (first.length() > 25) {
            productname.setError("First Name Can't Be Greater Then 10");
            return false;
        } else {
            productname.setError(null);
            productname.setErrorEnabled(false);
            return true; }
    }
    private boolean validatecompany()
    {
        String last = companyname.getEditText().getText().toString().trim();
        if (last.isEmpty()) {
            companyname.setError("Field Can't Be Empty");
            return false;
        } else if (last.length() >25) {
            companyname.setError("Last Name Can't Be Greater Then 10");
            return false;
        }
        else {
            companyname.setError(null);
            companyname.setErrorEnabled(false);
            return true; }
    }
    private boolean validateproblem()
    {
        String phone= problem.getEditText().getText().toString().trim();


        if (phone.isEmpty()) {
            problem.setError("Field Can't Be Empty");
            return false;
        }
        else {
            problem.setError(null);
            problem.setErrorEnabled(false);
            return true; }
    }
}