package com.example.zoom.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zoom.R
import com.example.zoom.utils.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebasestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setUpViews();
        sigUpBtnS.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        var email = emailBoxS.text.toString()
        var name = nameBoxS.text.toString()
        var password = passwordBoxS.text.toString()
        val user = User(name, email, password)

        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error creating user.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpViews() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebasestore = FirebaseFirestore.getInstance()
    }


}