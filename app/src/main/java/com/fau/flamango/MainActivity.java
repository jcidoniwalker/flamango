package com.fau.flamango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Context context;

    private EditText ET_Username;
    private EditText ET_Password;
    private Button B_Login;
    private Button B_Register;

    private HashMap<String, String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        users = new HashMap<>();
        users.put("admin", "admin");

        ET_Username = findViewById(R.id.editText);
        ET_Password = findViewById(R.id.editText2);
        B_Login = findViewById(R.id.button);
        B_Register = findViewById(R.id.button2);
        B_Login.setOnClickListener(this);

        B_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ET_Username.getText().toString().equals("") && !ET_Password.getText().toString().equals("")) {
                    if(!users.containsKey(ET_Username.getText().toString())) {
                        users.put(ET_Username.getText().toString(), ET_Password.getText().toString());
                        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(users.containsKey(ET_Username.getText().toString())) {
            if(users.get(ET_Username.getText().toString()).equals(ET_Password.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Details", Toast.LENGTH_SHORT).show();
        }
    }

    public static MySingleton getVolley() {
        return MySingleton.getInstance(context);
    }
}
