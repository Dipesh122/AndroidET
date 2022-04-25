package com.project.expenseTracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.expenseTracker.model.IncomeAndExpense;

import java.util.ArrayList;

public class CashBookFragment extends Fragment {

   private Button addBook;


    public CashBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_book, container, false);
        getActivity().setTitle("CashBook");


        addBook = view.findViewById(R.id.addNewBook);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddBookDialog();
            }
        });

        return view;
    }

    private void showAddBookDialog() {
        Dialog dialog = new Dialog(getActivity(),R.style.addBookAlterDialog);
        dialog.setTitle("Add New Book");
        dialog.setContentView(R.layout.add_new_bookpopup);
        dialog.show();

//        AddBookDialog addBookDialog = new AddBookDialog();
//        addBookDialog.show(getActivity().getSupportFragmentManager(), "add book dialog");
    }


}