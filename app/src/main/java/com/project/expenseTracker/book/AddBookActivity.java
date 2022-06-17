package com.project.expenseTracker.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.expenseTracker.HomeActivity;
import com.project.expenseTracker.R;
import com.project.expenseTracker.dbhelper.DBHelper;

public class AddBookActivity extends AppCompatActivity {

    CheckBox food, grocery, stationery, sports, miscellaneous;
    private Button add, cancel;
    private EditText name;
    private Spinner defaultCurrencySpinner;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setTitle("AddBook");

        //initialization
        defaultCurrencySpinner = findViewById(R.id.defaultCurrencySpinner);
        food = findViewById(R.id.checkBoxFood);
        grocery = findViewById(R.id.checkBoxShopping);
        stationery = findViewById(R.id.checkBoxStationery);
        sports = findViewById(R.id.checkBoxSports);
        miscellaneous = findViewById(R.id.checkBoxMis);
        add = findViewById(R.id.btnAdd);
        cancel = findViewById(R.id.btnCancel);
        name = findViewById(R.id.enterNameField);
        db = new DBHelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, HomeActivity.currencies);
        defaultCurrencySpinner.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book_name = db.insertBook(name.getText().toString());
                if (book_name != null) {
                    BookFragment.bookArrayList.add(0, book_name);
                    BookFragment.bookAdapter.notifyItemInserted(0);
                    BookFragment.recyclerView.scrollToPosition(0);
                } else {
                    Toast.makeText(AddBookActivity.this, "Please insert a name", Toast.LENGTH_SHORT).show();

                }
//                if (BookFragment.bookArrayList.size() > 0) {
//                    // any text to show if not present any book
//                }
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void processInsert(String n) {
        if (n.isEmpty() && n.length() < 5) {
            name.setError("Name Should be at least 5 character");
        }
        else {
            name.setError(null);
            Book res = new DBHelper(this).insertBook(n);
//            Toast toast = Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.BOTTOM,0,1100);
//            toast.show();
        }


    }
}