package com.project.expenseTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.expenseTracker.auth.LoginActivity;
import com.project.expenseTracker.book.AddBookActivity;
import com.project.expenseTracker.book.Book;
import com.project.expenseTracker.book.BookAdapter;
import com.project.expenseTracker.book.BookFragment;

import com.project.expenseTracker.dbhelper.DBHelper;
import com.project.expenseTracker.incomeandexpenses.IncomeFragment;

import java.util.ArrayList;



public class HomeActivity extends AppCompatActivity {

    public static BookAdapter bookAdapter;
    BottomNavigationView bottomNavigationView;
    public static ArrayList<Book> bookArrayList;
    DBHelper db;
    public static String[] currencies = new String[]{"NPR", "$", "€", "INR"};

//    Button btn_logout; for logout
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Cashbooks");
        mAuth = FirebaseAuth.getInstance();

        //initialization
        replaceFragment(new BookFragment());
        db = new DBHelper(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        add_book = findViewById(R.id.addNewBook);
        bookArrayList = db.getAllBooks();
        bookAdapter = new BookAdapter(bookArrayList);

        //bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.c_book:
                        replaceFragment(new BookFragment());
                        break;
//                     case R.id.help:
//                         replaceFragment(new Fragment());
//                        break;
                    case R.id.setting:
                        replaceFragment(new IncomeFragment());
                        break;
                }
                return true;
            }
        });

    }

    //replacing fragment by each id
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
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