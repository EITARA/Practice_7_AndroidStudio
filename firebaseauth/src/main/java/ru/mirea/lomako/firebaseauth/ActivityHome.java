package ru.mirea.lomako.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();}
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnSignOut) {
            mAuth.signOut();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
