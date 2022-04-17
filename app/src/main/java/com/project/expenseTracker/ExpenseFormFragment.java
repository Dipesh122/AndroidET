package com.project.expenseTracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.expenseTracker.dbhelper.DBHelper;
import com.project.expenseTracker.model.IncomeAndExpense;

import java.util.HashMap;


public class ExpenseFormFragment extends Fragment {

    String[] category = {"Food","Glossary","Stationary","Miscellaneous"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    TextInputLayout amount, add_remark, items;
    Button save_btn;
//    DBHelper DB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_expense_form, container, false);
        getActivity().setTitle("Add Cash In Entry");
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        adapterItems = new ArrayAdapter<String>(getActivity(),R.layout.dropdown_item,category);
        autoCompleteTextView.setAdapter(adapterItems);

//        crud data
        amount = view.findViewById(R.id.amount);
        add_remark = view.findViewById(R.id.remark);
        items = view.findViewById(R.id.inputTextCategory);
        save_btn = view.findViewById(R.id.save_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String amountTxt = amount.getEditText().getText().toString();
                String remarkTxt = add_remark.getEditText().getText().toString();
                 String itemTxt = items.getEditText().getText().toString();
                HashMap<String,Object> map = new HashMap<>();
                map.put("amount",amountTxt);
                map.put("remark",remarkTxt);
                map.put("item",itemTxt);

                FirebaseDatabase.getInstance().getReference().child("Post").push()
                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Amount Added.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Failed to Add.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
//        DB = new DBHelper(getActivity());

//      --------------------------------------------  actionlistener-------------------------------------------------------------------------
//        save_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String amountTxt = amount.getEditText().getText().toString();
//                String remarkTxt = add_remark.getEditText().getText().toString();
//                String itemTxt = items.getEditText().getText().toString();
//
//                Boolean check_insert = DB.insertData(amountTxt, remarkTxt, itemTxt);
//                if (check_insert == true) {
//                    Toast.makeText(getActivity(), "Amount Added", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getActivity(), "Amount Failed to Add", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ----------------------------------------------------------------------------------end dbhelper------------------------------------


        return view;
    }

}