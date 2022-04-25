package com.project.expenseTracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddBookDialog extends AppCompatDialogFragment {
   private EditText book_name;
   private Button save_btn;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_new_bookpopup, null);

        book_name = view.findViewById(R.id.edittext);
        save_btn = view.findViewById(R.id.save_btn);

        return builder.create();
    }
}
