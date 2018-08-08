package com.example.user.zebkey;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username, Keyname, Phonename, Email ,Password;
    private Button btnregist;
    private String Strusername,Strkeyname,Strphonename,Stremail,Strpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = (EditText) findViewById(R.id.username);
        Keyname = (EditText) findViewById(R.id.keyname);
        Phonename = (EditText) findViewById(R.id.phonename);
        Email = (EditText) findViewById(R.id.email);
        Password =(EditText) findViewById(R.id.password);
        btnregist = (Button) findViewById(R.id.btnRegister);
        //TextView login = (TextView)findViewById(R.id.lnkLogin);
        //login.setMovementMethod(LinkMovementMethod.getInstance());
        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/
        btnregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Strusername = Username.getText().toString().trim();
                Strkeyname = Keyname.getText().toString().trim();
                Strphonename = Phonename.getText().toString().trim();
                Stremail = Email.getText().toString().trim();
                Strpassword = Password.getText().toString().trim();

                //check string
                if (Strusername.equals("") || Strkeyname.equals("") ||
                        Strphonename.equals("") || Stremail.equals("") || Strpassword.equals("")) {

                    //dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Warning");
                    builder.setMessage("Please input data");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });
                    builder.show();

                }else{

                    UploadString();
                }



            }
        });
    }

    private void UploadString() {
        try{

            AddUser addUser = new AddUser(RegisterActivity.this,Strusername,Strkeyname,Strphonename,Stremail,Strpassword);
            addUser.execute();
            if(addUser.get().trim().contains("true")){
                Toast.makeText(RegisterActivity.this,"อัพโหลดเรียบร้อยแล้ว",Toast.LENGTH_SHORT).show();
                finish();

            }else {
                Toast.makeText(RegisterActivity.this,"ไม่สามารถอัพโหลดได้",Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}


