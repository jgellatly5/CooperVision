package com.jordangellatly.coopervision.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jordangellatly.coopervision.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private val TAG = "SignupActivity"
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btn_signup.setOnClickListener { signUp() }
        tv_switch_login.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp() {
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this@SignupActivity) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this@SignupActivity, "You have created a new account. Please sign in.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(this@SignupActivity, "Could not create user.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
