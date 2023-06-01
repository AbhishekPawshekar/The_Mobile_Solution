package com.example.the_mobile_solution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class admin_view extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout2;
    ActionBarDrawerToggle toggle2;
    NavigationView navigationView2;
    Toolbar toolbar2;
    FirebaseAuth fba=FirebaseAuth.getInstance();
    FirebaseUser singinuserdata=fba.getCurrentUser();
    FirebaseFirestore fbs=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);
        drawerLayout2=findViewById(R.id.drawerlayout1);
        navigationView2=findViewById(R.id.nav_view1);
        toolbar2=findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar2);
        toggle2=new ActionBarDrawerToggle(this,drawerLayout2,toolbar2,R.string.open,R.string.close);
        drawerLayout2.addDrawerListener(toggle2);
        toggle2.syncState();

        navigationView2.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new adminhomeclick()).commit();

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
        int id=item.getItemId();
        switch (id)
        {
            case R.id.home1:
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new adminhomeclick()).commit();
                break;
            case R.id.productdetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new adminproductdetailsclick()).commit();
                break;
            case R.id.productmul:
                Intent j=new Intent(getApplicationContext(),addproduct_select_category.class);
                startActivity(j);
                break;
            case R.id.userdetails:
                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new usermembersdetailsclick()).commit();
                break;
            case R.id.ordersdetails:

                getSupportFragmentManager().beginTransaction().replace(R.id.framedisplay1,new adminordersclick()).commit();
                break;
            case R.id.logout1:
                if(singinuserdata!=null)
                {
                    Intent i=new Intent(getApplicationContext(),main_page.class);
                    fba.signOut();
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"already Logout",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        drawerLayout2.closeDrawer(GravityCompat.START);
        return true;
    }

}