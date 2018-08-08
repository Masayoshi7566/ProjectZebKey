package com.example.user.zebkey;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class AddUser extends AsyncTask<Void, Void, String> {
    private Context context;
    private static final String stringUrl = "https://zebkey.azurewebsites.net/InsertData.php";
    private String Strusername, Strkeyname, Strphonename, Stremail, Strpassword;


    public AddUser(Context context,
                   String strusername,
                   String strkeyname,
                   String strphonename,
                   String stremail,
                   String strpassword) {

        this.context = context;
        Strusername = strusername;
        Strkeyname = strkeyname;
        Strphonename = strphonename;
        Stremail = stremail;
        Strpassword = strpassword;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("username", Strusername)
                    .add("keyname", Strkeyname)
                    .add("phonename", Strphonename)
                    .add("email", Stremail)
                    .add("password", Strpassword)
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