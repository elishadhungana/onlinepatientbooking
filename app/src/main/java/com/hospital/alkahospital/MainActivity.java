package com.hospital.alkahospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hospital.alkahospital.helper.RestClient;
import com.hospital.alkahospital.model.Login;
import com.hospital.alkahospital.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d("check","checkkkk");
        callLogin();
    }

    private void callRegister() {
        RestClient.RetroInterfaceAPI retroInterfaceAPI = RestClient.getClient();
        Call<Register> call=retroInterfaceAPI.getRegister("elishasa","ohaamarit@gmail.com","love1234","admin");
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
             Log.d("success","success");
                 Toast.makeText(MainActivity.this, response.body().getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Log.d("fail","fail");

            }
        });
    }

    private void callLogin() {
        RestClient.RetroInterfaceAPI retroInterfaceAPI = RestClient.getClient();
        Call<Login> call=retroInterfaceAPI.getLogin("elisha.dhungana.8@gmail.com","love123");
      call.enqueue(new Callback<Login>() {
          @Override
          public void onResponse(Call<Login> call, Response<Login> response) {
              Log.d("successlogin","successlogin");

          }

          @Override
          public void onFailure(Call<Login> call, Throwable t) {
              Log.d("faillogin","faillogin");

          }
      });
    }

}