package com.example.jeevanjyotandroidapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;

    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base_for_doctor, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);
        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            openHome();
        } else if (item.getItemId() == R.id.nav_chat) {
            openChatbot();
        }
        else if(item.getItemId()==R.id.nav_logout)
        {
            openLogOut();
        }
        else if(item.getItemId()==R.id.nav_settings)
        {
            openSetting();
        }
        else if(item.getItemId()==R.id.nav_share)
        {
            share();
        }

        drawerLayout.closeDrawers(); // Close the navigation drawer after selection
        return true;
    }

    private void openChatbot() {
        Intent intent = new Intent(this, ChatBot.class);
        startActivity(intent);
    }
    private void share()
    {

        try {
            // Specify the full path of your APK file
            File apkFile = new File("/storage/emulated/0/Download/app-debug.apk"); // Change path accordingly

            if (!apkFile.exists()) {
                Toast.makeText(this, "APK file not found!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get the URI using FileProvider
            Uri apkUri = FileProvider.getUriForFile(
                    this,
                    getApplicationContext().getPackageName() + ".provider",
                    apkFile);

            // Create Share Intent
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("application/vnd.android.package-archive");
            shareIntent.putExtra(Intent.EXTRA_STREAM, apkUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // Start share dialog
            startActivity(Intent.createChooser(shareIntent, "Share APK"));

        } catch (Exception e) {
            Toast.makeText(this, "Error sharing APK: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void openSetting()
    {
        Intent it=new Intent(this, SettingClass.class);
        startActivity(it);
    }
    private void openHome() {
        Intent intent = new Intent(this, DoctorHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Prevent activity stacking issues
    }
    private void openLogOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Prevent activity stacking issues

    }
    protected void allocateActivity(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}
