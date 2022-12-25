package com.hospital.alkahospital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.hospital.alkahospital.R;

public class RegisterActivity extends AppCompatActivity {
    EditText fullname,phoneNumber,password,re_password,address;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        fullname=findViewById(R.id.fullname_signup);
        phoneNumber=findViewById(R.id.phone_signup);
        password=findViewById(R.id.password_signup);
        re_password=findViewById(R.id.re_password_signup);
        address=findViewById(R.id.address_signup);
        signupButton=findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateFullname() | !validatePhone() | !validateAddress() |!validatePassword() |!validateRePassword() ) {
                    return;
                } else {
                    try {
                        Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private boolean validatePhone() {

        String passwordInput = phoneNumber.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            phoneNumber.setError(getResources().getText(R.string.valid_phone));
            return false;
        } else {
            phoneNumber.setError(null);
            return true;

        }

    }

    private boolean validateFullname() {

        String userNameInput = fullname.getText().toString().trim();
        if (userNameInput.isEmpty()) {
            // username.setError(getResources().getText(R.string.valid_phone_number_username_enter));
            fullname.setError(getResources().getText(R.string.valid_fullname));
            return false;
        } else {
            fullname.setError(null);
            return true;

        }

    }


    private boolean validateAddress() {

        String passwordInput = address.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            address.setError(getResources().getText(R.string.valid_address));
            return false;
        } else {
            address.setError(null);
            return true;

        }

    }


    private boolean validatePassword() {

        String passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError(getResources().getText(R.string.Password));
            return false;
        } else {
            password.setError(null);
            return true;

        }

    }

    private boolean validateRePassword() {

        String passwordInput = re_password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            re_password.setError(getResources().getText(R.string.Re_password));
            return false;
        } else {
            re_password.setError(null);
            return true;

        }

    }


}