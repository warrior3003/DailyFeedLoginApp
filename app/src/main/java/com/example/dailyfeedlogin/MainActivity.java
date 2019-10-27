package com.example.dailyfeedlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtuser, edtpass;
    Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtuser = findViewById(R.id.username);
        edtpass = findViewById(R.id.password);

        buttonLogin = findViewById(R.id.login);
        buttonRegister = findViewById(R.id.register);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();


            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });
    }

    private void login() {
        String user = edtuser.getText().toString();
        String pass = edtpass.getText().toString();

        if (user.equals("")||pass.equals(""))
        {
            Toast.makeText(this,"Username or Password is blank",Toast.LENGTH_SHORT).show();
        }
        else if (null!=checkUser(user,pass)){

            String userDb = checkUser(user,pass);
            Intent i = new Intent(MainActivity.this,empty.class);
            i.putExtra("uname",userDb);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "username or password not match", Toast.LENGTH_SHORT).show();
            edtuser.setText("");

            edtpass.setText("");
            edtuser.requestFocus();
        }




    }

    public String checkUser(String user,String pass){

        SQLiteDatabase db = openOrCreateDatabase("toy", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select id,user,pass from user where user = ? and pass = ?",new String []{user,pass});
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("username",MODE_PRIVATE).edit();
            sp.putString("uname",username);
            sp.apply();
            cursor.close();
            return username;
        } return null;
    }
}
