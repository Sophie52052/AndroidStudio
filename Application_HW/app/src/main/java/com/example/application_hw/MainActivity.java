package com.example.application_hw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {


    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.containermain);

        //manager = getSupportFragmentManager();

        changeFragment(new FragmentMain());
    }

    public void changeFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_fragment, f).commit();
    }





    @Override
    public void onBackPressed() {
        changeFragment(new FragmentMain());
    }


}
