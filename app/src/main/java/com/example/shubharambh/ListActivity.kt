package com.example.shubharambh

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageButton>(R.id.back_btn).setOnClickListener { finish() }

        val listView = findViewById<ListView>(R.id.list_view)
        val courses = listOf("BCA","MCA","BBA","MBA","BCOM","MCOM","BJMC","BTECH","MTECH",
            "BCA","MCA","BBA","MBA","BCOM","MCOM","BJMC","BTECH","MTECH")
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, courses)
    }
}