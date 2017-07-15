package com.umarzaii.classremindermanager.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.umarzaii.classremindermanager.Fragment.MainFragment;
import com.umarzaii.classremindermanager.Handler.FragmentHandler;
import com.umarzaii.classremindermanager.R;

public class MainActivity extends AppCompatActivity {

    private FragmentHandler fragmentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentHandler = new FragmentHandler(getSupportFragmentManager());

        fragmentHandler.startFragment(new MainFragment());
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
