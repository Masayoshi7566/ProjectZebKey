package com.example.user.zebkey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText EditEmail,Editpass;
    private Button btn_login;
    private String Stremail,Strpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditEmail = (EditText) findViewById(R.id.txtEmail);
        Editpass = (EditText) findViewById(R.id.txtPwd);
        btn_login = (Button) findViewById(R.id.btnLogin);
        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this,DetailUserActivity.class));
                Stremail = EditEmail.getText().toString().trim();
                Strpassword = Editpass.getText().toString().trim();
                LoginUser();
            }
        });

    }
    private void LoginUser() {
        try{

            UserLogin userLogin = new UserLogin(LoginActivity.this,Stremail,Strpassword);
            userLogin.execute();
            if(userLogin.get().trim().contains("true")){
                startActivity(new Intent(LoginActivity.this,CurrentLocationActivity.class));

            }else {
                Toast.makeText(LoginActivity.this,"ไม่สามารถ Login ได้ ",Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
