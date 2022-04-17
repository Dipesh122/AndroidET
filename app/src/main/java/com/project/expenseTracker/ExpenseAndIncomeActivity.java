package com.project.expenseTracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class ExpenseAndIncomeActivity extends AppCompatActivity {

    Button expenseOut_btn;


    ExpenseFormFragment expenseFormFragment = new ExpenseFormFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_and_income);
        expenseOut_btn = findViewById(R.id.expenseOut_btn);
        expenseOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expenseOut_btn.setVisibility(View.GONE);
//                getSupportFragmentManager().beginTransaction().replace(R.id.containerExpenseAndIncome, expenseFormFragment ).commit();

            }
        });

//        for action bar
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}