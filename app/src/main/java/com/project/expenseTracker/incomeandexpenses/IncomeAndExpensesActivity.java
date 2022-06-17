package com.project.expenseTracker.incomeandexpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.expenseTracker.R;

public class IncomeAndExpensesActivity extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_and_expenses);
        //getting intent from Income and expense activity
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("bookName");
        setTitle(title);
    }
}