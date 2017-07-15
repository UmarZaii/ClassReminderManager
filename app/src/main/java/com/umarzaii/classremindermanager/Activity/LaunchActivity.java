package com.umarzaii.classremindermanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.umarzaii.classremindermanager.Handler.DatabaseHandler;
import com.umarzaii.classremindermanager.R;

public class LaunchActivity extends AppCompatActivity {


    private DatabaseHandler databaseHandler;
    private FirebaseAuth.AuthStateListener fAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        databaseHandler = new DatabaseHandler();

        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    checkUserLogin();
                } else {
                    startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseHandler.getFirebaseAuth().addAuthStateListener(fAuthListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHandler.getFirebaseAuth().removeAuthStateListener(fAuthListener);
    }

    private void checkUserLogin() {

        if (databaseHandler.getCurrentUser().isEmailVerified()) {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        } else {
            databaseHandler.getFirebaseAuth().signOut();
            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            finish();
        }

    }

}
