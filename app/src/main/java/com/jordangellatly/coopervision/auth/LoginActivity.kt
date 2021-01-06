package com.jordangellatly.coopervision.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.search.FilterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener { login()}
        tv_switch_signup.setOnClickListener {
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
            else -> mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) { task ->
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
