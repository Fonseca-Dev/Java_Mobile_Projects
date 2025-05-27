package org.code.poupa;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {
    protected BottomNavManager bottomNavManager;

    protected void setupBottomNavigation() {
        BottomNavigationView bottomNavView = findViewById(R.id.Main_bottom_navigation);
        bottomNavManager = new BottomNavManager(this, bottomNavView);
    }
}