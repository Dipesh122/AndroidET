package com.project.expenseTracker;

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
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.expenseTracker.model.IncomeAndExpense;

import java.util.ArrayList;

public class CashBookFragment extends Fragment {

   Button addBook;
   Fragment fm = new ExpenseFormFragment();

//   RecyclerView recyclerView;
//   FirebaseDatabase db = FirebaseDatabase.getInstance();
//   DatabaseReference databaseReference;
//   MyAdapter myAdapter;
//   ArrayList<IncomeAndExpense> list;
    public CashBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_book, container, false);
        getActivity().setTitle("CashBook");

//        recyclerView = view.findViewById(R.id.recycler);
//        databaseReference = FirebaseDatabase.getInstance().getReference("Post");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        list = new ArrayList<>();
//        myAdapter = new MyAdapter(getActivity(),list);
//        recyclerView.setAdapter(myAdapter);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    IncomeAndExpense incomeAndExpense = dataSnapshot.getValue(IncomeAndExpense.class);
//                    list.add(incomeAndExpense);
//
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        addBook = view.findViewById(R.id.addNewBook);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fm).commit();
            }
        });


        return view;
    }

}