package com.project.expenseTracker.book;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.project.expenseTracker.HomeActivity;
import com.project.expenseTracker.R;
import com.project.expenseTracker.auth.LoginActivity;
import com.project.expenseTracker.dbhelper.DBHelper;

import java.util.ArrayList;


public class BookFragment extends Fragment {

    public static RecyclerView recyclerView;
    public static BookAdapter bookAdapter;
    public static ArrayList<Book> bookArrayList;
    Button add_book;
    DBHelper db;
    EditText searchbar;
    public static String[] currencies = new String[]{"NPR", "$", "€", "INR"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        add_book = view.findViewById(R.id.addNewBook);
        db = new DBHelper(getContext());
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookArrayList = db.getAllBooks();
        bookAdapter = new BookAdapter(bookArrayList);
        recyclerView.setAdapter(bookAdapter);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(new Space(35));
        searchbar = view.findViewById(R.id.search_edt);

        // searchbar
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

        //implementation of listener and recyclerView
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddBookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        return view;
    }

    private void filter(String text) {
        ArrayList<Book> filterList = new ArrayList<>();
        for (Book book : bookArrayList) {
            if (book.getName().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(book);
            }
        }
        bookAdapter.filterList(filterList);

    }
}