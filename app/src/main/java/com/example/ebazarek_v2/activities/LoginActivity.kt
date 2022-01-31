package com.example.ebazarek_v2.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ebazarek_v2.R
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val FORM = "aplication/x-www-form-urlencoded".toMediaType()
    private val type = "application/json"


    private fun httpPost(
        url: String,
        body: RequestBody,
        success: (response: Response) -> Unit,
        failure: () -> Unit
    ) {
        val request = Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Content-Type", type)
            .addHeader("Accept", type)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                println("Failed")
            }

            override fun onResponse(call: Call, response: Response) {
                success(response)
            }

        })

    }

    fun login(login: String, password: String) {
        Toast.makeText(
            this, "Logowanie ($login: $password)",
            Toast.LENGTH_SHORT
        ).show()

        val url = "http://10.0.2.2:3000/login"
        //val body = ("session[user_login]=$login&session[password]=$password").toRequestBody(FORM)
        val body = ("{\"session\":{\"email\":\"$login\",\"password\":\"$password\"}}").toRequestBody(type.toMediaType())
        httpPost(url, body,
            fun(response: Response) {
                println("Success")

                val resp = response.body!!.string()
                val json = JSONObject(resp)

                if (json.has("token")) {
                    this.runOnUiThread {
                        Toast.makeText(this, json["token"] as String, Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@LoginActivity, CategoriesActivity::class.java)
                        startActivity(intent)
                    }
                } else if (json.has("message")) {
                    this.runOnUiThread {
                        Toast.makeText(this, json["message"] as String, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            fun() {
                println("Failed")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnContinueWithoutAccount = findViewById<Button>(R.id.btnContinueWithoutAccount)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnContinueWithoutAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, CategoriesActivity::class.java)
            startActivity(intent)
        }

        if (edtLoginName.requestFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }

        btnLogin.setOnClickListener {
            val login = edtLoginName.text.toString()
            val password = edtLoginPassword.text.toString()
            login(login, password)
        }


    }


}

