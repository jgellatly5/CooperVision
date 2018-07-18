package com.jordangellatly.coopervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.tv_switch_login)
    TextView tvSwitchLogin;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick({R.id.btn_signup, R.id.tv_switch_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                signUp();
                break;
            case R.id.tv_switch_login:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void signUp() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "You have created a new account. Please sign in.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(SignupActivity.this, "Could not create user.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
