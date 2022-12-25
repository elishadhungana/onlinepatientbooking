package com.hospital.alkahospital.helper;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hospital.alkahospital.model.Login;
import com.hospital.alkahospital.model.Register;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class RestClient {

    private static RetroInterfaceAPI retroInterfaceAPI;
    public static final String URL = "http://192.168.1.78:4000/users/";

    public static RetroInterfaceAPI getClient() {

        if (retroInterfaceAPI == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("Retrofit", message);
                }
            });

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // sap request ko kun thau log garne. Body garo bhane sap aauxa

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                  //  .addInterceptor(new HeaderAuthoriztionInterceptor())
                    .addInterceptor(loggingInterceptor)
                    .build();


            Retrofit client = new Retrofit.Builder()
                    .client(okClient)
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            retroInterfaceAPI = client.create(RetroInterfaceAPI.class);
        }

        return retroInterfaceAPI;
    }


    public interface RetroInterfaceAPI {



        @FormUrlEncoded
        @POST("create")
        Call<Register> getRegister(@Field("name") String name,
                                   @Field("email") String email,
                                   @Field("password") String password,
                                   @Field("role") String role
        );

        @FormUrlEncoded
        @POST("login")
        Call<Login> getLogin(@Field("email") String email,
                             @Field("password") String password


                             );



    }



}

