package com.example.shubharambh

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        drawerLayout=findViewById(R.id.drawer_layout)
        val navigationView=findViewById<NavigationView>(R.id.navigation_view)
        val navigationOpenBtn=findViewById<ImageButton>(R.id.drawer_btn)
        val headerNav=navigationView.getHeaderView(0)
        val navigationCloseBtn=headerNav.findViewById<ImageButton>(R.id.close_drawer_btn)
        val recyclerView=findViewById<RecyclerView>(R.id.recycler_view)

        navigationOpenBtn.setOnClickListener{drawerLayout.open()}
        navigationCloseBtn.setOnClickListener {drawerLayout.close()}

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.pie_chart -> startActivity(Intent(this, ChartActivity::class.java))
                R.id.list -> startActivity(Intent(this, ListActivity::class.java))
            }
            drawerLayout.closeDrawers()
            true
        }

        val personList = listOf(
            Person("Shashank Tiwari", "Mobile Developer"),
            Person("Atul Maurya", "Software Engineer"),
            Person("Alok Verma", "Product Manager"),
            Person("Rajat", "UX Designer"),
            Person("Raj", "Data Scientist"),
            Person("Shubham Pandey", "QA Analyst"),
            Person("Anuj Dwivedi", "DevOps Engineer"),
            Person("Aryan", "HR Manager"),
            Person("Shivam", "Business Analyst"),
            Person("Nikhil", "Tech Lead"),
            Person("Rahul", "Founder"),
            Person("Satish", "Co-Founder")
        )
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= RecyclerAdapter(this,personList)

    }

    override fun onBackPressed() {
        if (drawerLayout.isOpen) drawerLayout.close()
        else super.onBackPressed()
    }
}