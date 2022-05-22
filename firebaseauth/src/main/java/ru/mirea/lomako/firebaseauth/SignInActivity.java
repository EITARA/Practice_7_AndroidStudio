package ru.mirea.lomako.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ru.mirea.lomako.firebaseauth.MainActivity.class.getSimpleName();
    private String userEmail;
    private String userPassword;
    private Array createAccountInputsArray;
    private Button btnCreateAccount2;
    private EditText etEmail,etPassword,etConfirmPassword;
    // START declare_auth
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Views
        btnCreateAccount2=(Button) findViewById(R.id.btnCreateAccount) ;
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etConfirmPassword=(EditText) findViewById(R.id.etConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }
    // [START on_start_check_user]

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this,ActivityHome.class);
            startActivity(intent);
        }
    }
    private boolean validateForm() {
        boolean valid = true;
        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required.");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        String password = etPassword.getText().toString();
        if (!TextUtils.isEmpty(password)
        ) { valid = true; }
        else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required.");
            valid = false;
        }
        else {
            Toast.makeText(ru.mirea.lomako.firebaseauth.SignInActivity.this,"passwords are not matching !",Toast.LENGTH_SHORT).show();
        }
        return valid;
    }
    private void createAccount(String email, String password) {

        // [START create_user_with_email]

    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ru.mirea.lomako.firebaseauth.SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(ru.mirea.lomako.firebaseauth.SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnCreateAccount2) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (i == R.id.btnSignIn) {
            signIn(etEmail.getText().toString(), etPassword.getText().toString());
        }
    }


}

