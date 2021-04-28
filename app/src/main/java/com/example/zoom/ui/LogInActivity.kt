package com.example.zoom.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zoom.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        setUpViews()
        logInBtnL.setOnClickListener {
            logInUser()
        }

        signUpBtnL.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun logInUser() {
        progressDialog.show()
        val email = emailBoxL.text.toString()
        val password = passwordBoxL.text.toString()


        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email/password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setUpViews() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait.....")
        firebaseAuth = FirebaseAuth.getInstance()

    }
}