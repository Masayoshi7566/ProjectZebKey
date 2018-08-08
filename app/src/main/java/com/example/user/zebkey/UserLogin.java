package com.example.user.zebkey;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class UserLogin extends AsyncTask<Void,Void,String> {
    Context context;
    private static final String stringUrl = "https://zebkey.azurewebsites.net/SelectUser.php";
    private String Email,Password;

    public UserLogin(Context context, String email, String password) {
        this.context = context;
        Email = email;
        Password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("email", Email)
                    .add("password", Password)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(stringUrl).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
