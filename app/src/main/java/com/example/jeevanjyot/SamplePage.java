package com.example.jeevanjyot;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.util.Log;

public class SamplePage extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ImageView navButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_page);

        // Initialize Views
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navButton = findViewById(R.id.navButton);

        // Debugging Check
        Log.d("DEBUG", "Checking drawerLayout: " + (drawerLayout != null));

        // Setup ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true); // Ensure the indicator (hamburger icon) is enabled
        toggle.syncState();

        // Handle Click Event for Nav Button
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "NavButton Clicked!");
                if (drawerLayout != null) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    Log.d("DEBUG", "Drawer should open now.");
                } else {
                    Log.e("ERROR", "drawerLayout is NULL");
                }
            }
        });

        // Handle Navigation Menu Clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                // Using if-else if instead of switch-case
                if (id == R.id.menu_find_doctor) {
                    Log.d("MENU", "Find Doctor Clicked");
                } else if (id == R.id.menu_appointment) {
                    Log.d("MENU", "Book Appointment Clicked");
                } else if (id == R.id.menu_medicine_scanner) {
                    Log.d("MENU", "Medicine Scanner Clicked");
                } else if (id == R.id.menu_sos) {
                    Log.d("MENU", "Emergency SOS Clicked");
                } else if (id == R.id.menu_chatbot) {
                    Log.d("MENU", "Chatbot Clicked");
                } else if (id == R.id.menu_health_reports) {
                    Log.d("MENU", "Health Reports Clicked");
                }

                // Close drawer after clicking an item
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Close drawer on back press if open
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
