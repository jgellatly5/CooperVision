package com.jordangellatly.coopervision.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jordangellatly.coopervision.search.FilterActivity
import com.jordangellatly.coopervision.R

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import de.hdodenhof.circleimageview.CircleImageView

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        mAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener { v -> login()}
        tv_switch_signup.setOnClickListener { v ->
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()

        when {
            email == "" -> Toast.makeText(this, "Please enter your email.", Toast.LENGTH_SHORT).show()
            password == "" -> Toast.makeText(this, "Please enter your password.", Toast.LENGTH_SHORT).show()
            else -> mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LoginActivity, FilterActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this@LoginActivity, "Authentication failed: $email $password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
