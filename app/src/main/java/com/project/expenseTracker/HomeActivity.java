package com.project.expenseTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

//    Button btn_logout; for logout
    FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    CashBookFragment cashBookFragment= new CashBookFragment();
//    SettingFragment settingFragment= new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        btn_logout = findViewById(R.id.logout_btn); for logout
        mAuth = FirebaseAuth.getInstance();




//        bottom navigation view is initialize here
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
      getSupportFragmentManager().beginTransaction().replace(R.id.container, cashBookFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.c_book:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,cashBookFragment).commit();
                        return true;

//                    case R.id.setting:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();
//                        return true;

                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}