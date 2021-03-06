package com.fau.flamango;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.fau.flamango.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class SecondActivity extends AppCompatActivity {

    private static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("userobj"); // This is passed from MainActivity

        /* Initialize the bottom navigation panel */
        BottomNavigationView navView = findViewById(R.id.nav_view);

        /* Listen for logout click */
        navView.getMenu().findItem(R.id.navigation_signout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                /* Send the user back to the login/registration activity */
                Intent i = new Intent(getApplication(), MainActivity.class);
                startActivity(i);
                return true;
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_signout).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    /**
     * The User object of the logged in user.
     * @return User
     */
    public static User getUser() {
        return user;
    }

}
