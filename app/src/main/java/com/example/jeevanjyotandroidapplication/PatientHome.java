package com.example.jeevanjyotandroidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jeevanjyotandroidapplication.databinding.ActivityDashboardBinding;

public class PatientHome extends DrawerBaseForPatient{
    ActivityDashboardBinding activityDashboardBinding;
    LinearLayout findDoctor,findMedicine,emergency,billing,report,remainder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        // Inflate Doctor's layout inside the drawer
        getLayoutInflater().inflate(R.layout.activity_main_for_patient, activityDashboardBinding.contentFrame, true);
        findDoctor=findViewById(R.id.findDoctor);
        findMedicine=findViewById(R.id.findMedicine);
        emergency=findViewById(R.id.Emergency);
        report=findViewById(R.id.report);
        remainder=findViewById(R.id.remainder);
        billing=findViewById(R.id.billing);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(PatientHome.this,Emergency.class);
                startActivity(it);
            }
        });

    }
}
