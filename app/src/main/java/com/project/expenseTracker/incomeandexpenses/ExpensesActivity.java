package com.project.expenseTracker.incomeandexpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.project.expenseTracker.R;
import com.project.expenseTracker.dbhelper.DBHelper;

public class ExpensesActivity extends AppCompatActivity {

    DBHelper db;
    AutoCompleteTextView autoCompleteTextView;
    Button btnAdd;
    private TextInputLayout amount, remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        setTitle("ExpenseOut");
        String[] category = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.dropdown_item, category);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        amount = findViewById(R.id.amount);
        remark = findViewById(R.id.remark);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String remark_f = remark.getEditText().getText().toString();
                String res = new DBHelper(getApplicationContext()).insertExpense(remark_f);
                Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
            }
        });
    }
}