package com.project.expenseTracker.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.project.expenseTracker.R;
import com.project.expenseTracker.model.User;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout name, email, password, retype_password;
    Button sign_up;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        retype_password = findViewById(R.id.confirm_password);
        sign_up = findViewById(R.id.signUp_btn);

        //initialize firebase auth
        mAuth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateName() && validateEmail()  && validatePassword()) {
                    String username = name.getEditText().getText().toString();
                    String field_email = email.getEditText().getText().toString();
                    String field_password = password.getEditText().getText().toString();
                    String field_confirm_password = retype_password.getEditText().getText().toString();

                    mAuth.createUserWithEmailAndPassword(field_email,field_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                                User user = new User(username,field_email);
                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class  ));
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterActivity.this, "SingUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    private Boolean validateName() {
        String val = name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,15}\\z";

        if (val.isEmpty()){
            name.setError("Field cannot be empty");
            return false;
        } else if (val.length()<5){
            name.setError("Name is too short");
            return false;
        } else if (!val.matches(noWhiteSpace)){
            name.setError("White spaces are not allowed");
            return false;
        } else if (val.length()>15){
            name.setError("Name is too long");
            return false;
        }
        else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String val2 = retype_password.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\s+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;

        } else if (val.length()<=6){
            password.setError("Password is too weak");
            return false;
        } else if (!val2.equals(val)) {
            retype_password.setError("Password doesn't match");
            return false;
        }
        else {
            password.setError(null);
            retype_password.setError(null);
            password.setErrorEnabled(false);
            retype_password.setErrorEnabled(false);
            return true;
        }

    }

}
