package com.example.ebazarek_v2.activities

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import com.example.ebazarek_v2.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
                val intent = Intent(this, LoginActivity::class.java)

                startActivity(intent)
            },
            1500
        )

    }
}