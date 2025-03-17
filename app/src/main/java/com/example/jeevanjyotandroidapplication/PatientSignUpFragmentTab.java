package com.example.jeevanjyotandroidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PatientSignUpFragmentTab extends Fragment {
    EditText email,pass,mobile,username;

    Button sign_up;
    float v=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup root=(ViewGroup)inflater.inflate(R.layout.sign_upfragment,container,false);
        email=root.findViewById(R.id.email);
        pass=root.findViewById(R.id.pass);
        username=root.findViewById(R.id.name);
        mobile=root.findViewById(R.id.Mobile);
        sign_up=root.findViewById(R.id.sign_up);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        username.setTranslationX(800);
        mobile.setTranslationX(800);
        sign_up.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        username.setAlpha(v);
        mobile.setAlpha(v);
        sign_up.setAlpha(v);


        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mobile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        sign_up.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(),PatientHome.class);
                startActivity(it);
            }
        });

        return root;
    }
}
