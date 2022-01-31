package com.example.ebazarek_v2.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.ebazarek_v2.R
import com.example.ebazarek_v2.model.CategoriesResponse
import com.example.ebazarek_v2.model.Category
import com.example.ebazarek_v2.services.CategoriesService
import com.example.ebazarek_v2.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        loadCategories()
    }

    private fun loadCategories() {
        val categoriesListView = findViewById<ListView>(R.id.categoriesListView)
        val service = ServiceBuilder.buildService(CategoriesService::class.java)

        service.getCategories().enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>?,
                response: Response<CategoriesResponse>?
            ) {
                if (response!!.isSuccessful) {
                    categoriesListView.adapter = ArrayAdapter(
                        this@CategoriesActivity,
                        android.R.layout.simple_list_item_1,
                        response.body()?.categories!!
                    )
                } else {
                    Log.d("tag", "Something went wrong ${response.message()}")
                    Toast.makeText(
                        this@CategoriesActivity,
                        "Something went wrong ${response.message()}",
                        LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>?, t: Throwable?) {
                Log.d("tag", "Something went wrong $t")
                Toast.makeText(this@CategoriesActivity, "Something went wrong $t", LENGTH_SHORT)
                    .show()
            }
        })

        categoriesListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ProductsActivity::class.java)
            val item = parent.adapter.getItem(position) as Category
            intent.putExtra("categoryId", item.id)
            startActivity(intent)
        }
    }
}




