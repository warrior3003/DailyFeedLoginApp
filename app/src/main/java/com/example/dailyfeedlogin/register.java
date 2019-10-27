package com.example.dailyfeedlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText edtcnfpass, edtpass, edtuser;
    Button btnadd, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtuser = findViewById(R.id.username);
        edtpass = findViewById(R.id.password);
        edtcnfpass = findViewById(R.id.confirmpassword);
        btnadd = findViewById(R.id.add);

        btnLogin = findViewById(R.id.login);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(register.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert();







            }
        });

    }

    public void insert()
    {
        try {
            String username = edtuser.getText().toString();
            String password = edtpass.getText().toString();
            String confirmpassword = edtcnfpass.getText().toString();



            SQLiteDatabase DB = openOrCreateDatabase("toy", Context.MODE_PRIVATE, null);
            DB.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR, pass VARCHAR, conpass VARCHAR)");
            if (!password.equals(confirmpassword))
            {
                Toast.makeText(register.this, "PASSWORD NOT MATCHING",  Toast.LENGTH_LONG).show();
            }
            else
            {
                String sql = "insert into user(user,pass,conpass)values(?,?,?)";
                SQLiteStatement statement = DB.compileStatement(sql);
                statement.bindString(1,username);
                statement.bindString(2,password);
                statement.bindString(3,confirmpassword);
                statement.execute();
                Toast.makeText(this,"RECORD ADDED",Toast.LENGTH_SHORT).show();

                edtuser.setText("");
                edtpass.setText("");
                edtcnfpass.setText("");
                edtuser.requestFocus();
            }
        }
        catch (Exception ex)
        {

            Toast.makeText(this,"Record fail",Toast.LENGTH_SHORT).show();

        }

    }}






