package com.example.ebazarek_v2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ebazarek_v2.R
import kotlinx.android.synthetic.main.activity_shoppingcart.*

class ShoppingCartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppingcart)


        val order = findViewById<Button>(R.id.orderButton)
        order.setOnClickListener {
            val intent = Intent(this@ShoppingCartActivity, OrderActivity::class.java)
            startActivity(intent)
        }

    }
}