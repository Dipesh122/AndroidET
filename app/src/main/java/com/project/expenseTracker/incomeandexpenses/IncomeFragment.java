package com.project.expenseTracker.incomeandexpenses;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.project.expenseTracker.R;
import com.project.expenseTracker.dbhelper.DBHelper;


public class IncomeFragment extends Fragment {
    DBHelper db;
    AutoCompleteTextView autoCompleteTextView;
    Button btnAdd;
    private TextInputLayout amount, remark;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        String[] category = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.dropdown_item, category);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        btnAdd = view.findViewById(R.id.btnAdd);
        amount = view.findViewById(R.id.amount);
        remark = view.findViewById(R.id.remark);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String remark_field = remark.getEditText().getText().toString();
                Toast.makeText(getContext(),remark_field,Toast.LENGTH_SHORT).show();
                String db = new DBHelper(getContext()).insertExpense(remark_field);

                Toast.makeText(getContext(),db,Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        String[] category = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.dropdown_item, category);
        autoCompleteTextView.setAdapter(adapter);
    }
}