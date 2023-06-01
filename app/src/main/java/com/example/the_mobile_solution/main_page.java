package com.example.the_mobile_solution;


import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class main_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView signupactionbar,loginactionbar,singinfullname,singinuser,singinemail;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Toolbar toolbar;

    FirebaseFirestore fbs= FirebaseFirestore.getInstance();
    FirebaseAuth fba ;
    FirebaseUser singinuserdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.nav_view);
        signupactionbar=findViewById(R.id.signupbar);
        loginactionbar=findViewById(R.id.loginbar);
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new homeclick()).commit();
        navigationView.setNavigationItemSelectedListener(this);

updatenavheader();

        signupactionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),registration_page.class);
                startActivity(j);
            }
        });
        loginactionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),login_page.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure You Want To Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        onnavigationitemselect(item);
        return true;
    }
public void onnavigationitemselect(MenuItem item){
    int id=item.getItemId();
    switch (id)
    {
        case R.id.home:
            getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new homeclick()).commit();
            break;
        case R.id.account:
            if(singinuserdata!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new accountclick()).commit();

            }else{
                Toast.makeText(getApplicationContext(),"Plz.Login",Toast.LENGTH_SHORT).show();
            }
            break;
        case R.id.orders:
            if(singinuserdata!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new orderclick()).commit();

            }else{Toast.makeText(getApplicationContext(),"Plz.Login",Toast.LENGTH_SHORT).show();}
           break;
        case R.id.customer_service:
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new customerclick()).commit();

            break;
        case R.id.mobile_screen_display:
            getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new mobiledisplayclick()).commit();
            break;
        case R.id.mobile_batter:
            getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new mobilebatteryclick()).commit();
            break;
        case R.id.charger:
            getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new chargerclick()).commit();
            break;
        case R.id.help:
            Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay,new helpclick()).commit();
            break;
        case R.id.deleteaccount:
            if(singinuserdata!=null) {
                singinuserdata.delete();
                Intent i=new Intent(getApplicationContext(),main_page.class);
                fba.signOut();
                startActivity(i);
                fbs.collection("Customer").document(singinuserdata.getEmail()).delete();
            }
            else{
                Toast.makeText(getApplicationContext(),"Not Able To Delete Account",Toast.LENGTH_SHORT).show();
            }
            break;
        case R.id.logout:
            if(singinuserdata!=null)
            {
                Intent i=new Intent(getApplicationContext(),main_page.class);
                fba.signOut();
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(),"already Logout",Toast.LENGTH_SHORT).show();
            }


    }

    drawerLayout.closeDrawer(GravityCompat.START);

}
    public void updatenavheader(){
        fba=FirebaseAuth.getInstance();
        singinuserdata=fba.getCurrentUser();
        navigationView=findViewById(R.id.nav_view);
        View headerview=navigationView.getHeaderView(0);
        singinfullname=headerview.findViewById(R.id.singinfullname);
        singinuser=headerview.findViewById(R.id.singinusername);
        singinemail=headerview.findViewById(R.id.singinemail);
if(singinuserdata!=null) {

    singinemail.setText(singinuserdata.getEmail());
fbs.collection("Customer").document(singinuserdata.getEmail()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        if(task.isSuccessful()){
            singinfullname.setText( (String) task.getResult().get("First_Name")+" "+task.getResult().get("Last_Name"));
            singinuser.setText((String) task.getResult().get("User_Name"));
        }
    }
});
}
}
}