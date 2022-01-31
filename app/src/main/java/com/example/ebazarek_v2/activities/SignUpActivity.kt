package com.example.ebazarek_v2.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ebazarek_v2.R


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btnBacktoLogin = findViewById<Button>(R.id.btnBackToLogin)
        btnBacktoLogin.setOnClickListener {
            finish()
        }
    }
}
