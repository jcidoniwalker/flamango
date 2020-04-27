package com.fau.flamango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fau.flamango.dao.UserDAO;
import com.fau.flamango.models.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Context context;

    /* Login GUI */
    private EditText ET_Username;
    private EditText ET_Password;
    private Button B_Login;
    private Button B_Register;

    private static UserDAO userDAO = new UserDAO();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        ET_Username = findViewById(R.id.editText);
        ET_Password = findViewById(R.id.editText2);
        B_Login = findViewById(R.id.button);
        B_Register = findViewById(R.id.button2);
        B_Login.setOnClickListener(this);


        B_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ET_Username.getText().toString();
                String password = ET_Password.getText().toString();

                if(!username.equals("") && !password.equals("")) {
                    if(!userDAO.exists(username)) {
                        user = userDAO.create(username, password); // Create a new User
                        Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                        /* Prepare the next Activity */
                        Intent intent = new Intent(context, SecondActivity.class);
                        intent.putExtra("userobj", user); // Pass the User object to the next activity
                        startActivity(intent); // Start the Activity
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String username = ET_Username.getText().toString();
        String password = ET_Password.getText().toString();

        if(userDAO.exists(username)) {
            user = userDAO.login(username, password);
            if(user != null) {
                Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_LONG).show();
                /* Prepare the next Activity */
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("userobj", user); // Pass the User object to the next activity
                startActivity(intent); // Start the Activity
            } else {
                Toast.makeText(getApplicationContext(), "Invalid details", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid details.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Return a Volley instance from the context of our MainActivity
     * Future: This could be moved to SecondActivity, as it is only used once a user is logged in.
     * @return MySingleton
     */
    public static MySingleton getVolley() {
        return MySingleton.getInstance(context);
    }
}
