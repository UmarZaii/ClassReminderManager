package com.umarzaii.classremindermanager.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.umarzaii.classremindermanager.Handler.DatabaseHandler;
import com.umarzaii.classremindermanager.R;

public class ActivationActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;

    private TextView txtAdminEmail;
    private Button btnResendEmail, btnContinue;
    private ProgressDialog progressDialog;

    private String adminEmail;
    private String adminPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        databaseHandler = new DatabaseHandler();

        txtAdminEmail = (TextView)findViewById(R.id.txtAdminEmail);
        btnResendEmail = (Button)findViewById(R.id.btnResendEmail);
        btnContinue = (Button)findViewById(R.id.btnContinue);

        Intent intent = getIntent();
        adminEmail = intent.getStringExtra("adminEmail");
        adminPass = intent.getStringExtra("adminPass");

        final String strUserEmail = databaseHandler.getCurrentUser().getEmail();
        txtAdminEmail.setText(strUserEmail);

        btnResendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHandler.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivationActivity.this, "Verification email sent to " + strUserEmail, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActivationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Checking, Please Wait...");
                progressDialog.show();

                databaseHandler.getFirebaseAuth().signOut();
                databaseHandler.getFirebaseAuth().signInWithEmailAndPassword(adminEmail,adminPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (databaseHandler.getCurrentUser().isEmailVerified()) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ActivationActivity.this, "Please verify your e-mail first", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }

    @Override
    public void onBackPressed() {
        databaseHandler.getFirebaseAuth().signOut();
        super.onBackPressed();
    }

}
