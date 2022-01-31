package com.example.ebazarek_v2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ebazarek_v2.R

class OrderActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)


        val cont = findViewById<Button>(R.id.continueButton)
        cont.setOnClickListener {
            val intent = Intent(this@OrderActivity, CategoriesActivity::class.java)
            startActivity(intent)
        }
    }


}
