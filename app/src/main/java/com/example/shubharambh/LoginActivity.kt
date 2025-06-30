package com.example.shubharambh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userIdET=findViewById<EditText>(R.id.userId)
        val passwordET=findViewById<EditText>(R.id.password)
        val submitBtn=findViewById<Button>(R.id.btnLogin)

        submitBtn.setOnClickListener {
            if(userIdET.text.toString().trim()!="UserId") userIdET.error="Enter Valid UserId"
            else if(passwordET.text.toString().trim()!="Password") passwordET.error="Enter Valid Password"
            else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}