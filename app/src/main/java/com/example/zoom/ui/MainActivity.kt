package com.example.zoom.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.zoom.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                var intent = Intent(this@MainActivity, LogInActivity::class.java)
                startActivity(intent)
            }
        }, 2000)

    }
}