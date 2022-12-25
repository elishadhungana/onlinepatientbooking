package com.hospital.alkahospital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hospital.alkahospital.MainActivity;
import com.hospital.alkahospital.R;
import com.hospital.alkahospital.helper.RestClient;
import com.hospital.alkahospital.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginButton;
    TextView go_to_signup;
    String userNameInput;
    String passwordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username=findViewById(R.id.username_login);
        password=findViewById(R.id.password_login);
        loginButton=findViewById(R.id.login_button);
        go_to_signup=findViewById(R.id.go_to_signup);

        go_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword() ) {
                    return;
                } else {
                    try {
//                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
                        callLogin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

    }

    private boolean validateUsername() {

         userNameInput = username.getText().toString().trim();
        if (userNameInput.isEmpty()) {
           // username.setError(getResources().getText(R.string.valid_phone_number_username_enter));
            username.setError(getResources().getText(R.string.valid_username));
            return false;
        } else {
            username.setError(null);
            return true;

        }

    }
    private boolean validatePassword() {

         passwordInput = password.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError(getResources().getText(R.string.valid_password));
            return false;
        } else {
            password.setError(null);
            return true;

        }

    }

    private void callLogin() {

        RestClient.RetroInterfaceAPI retroInterfaceAPI = RestClient.getClient();
        Call<Login> call=retroInterfaceAPI.getLogin(userNameInput,passwordInput);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                Log.d("successlogin","successlogin");
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("faillogin","faillogin");

            }
        });
    }
}