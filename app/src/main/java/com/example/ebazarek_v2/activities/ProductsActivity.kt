package com.example.ebazarek_v2.activities

import com.example.ebazarek_v2.adapters.ProductsAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ebazarek_v2.R
import com.example.ebazarek_v2.databinding.ActivityProductsBinding
import com.example.ebazarek_v2.model.CategoriesResponse
import com.example.ebazarek_v2.model.Product
import com.example.ebazarek_v2.model.ProductsResponse
import com.example.ebazarek_v2.services.CategoriesService
import com.example.ebazarek_v2.services.ProductsService
import com.example.ebazarek_v2.services.ServiceBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityProductsBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityProductsBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_products)
       // setSupportActionBar(binding.toolbar)
        loadProducts()
    }


    private fun loadProducts() {
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        val service = ServiceBuilder.buildService(ProductsService::class.java)

        service.getProducts(intent.getIntExtra("categoryId", 0)).enqueue(object : Callback<ProductsResponse> {
                override fun onResponse(
                    call: Call<ProductsResponse>?,
                    response: Response<ProductsResponse>?
                ) {
                    if (response!!.isSuccessful) {
                        recyclerView.layoutManager = GridLayoutManager(this@ProductsActivity, 1)
                        recyclerView.adapter = ProductsAdapter(response.body()?.products!!)
                    } else {
                        Log.d("tag", "Something went wrong ${response.message()}")
                        Toast.makeText(
                            this@ProductsActivity,
                            "Something went wrong ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                    Log.d("tag", "Something went wrong $t")
                    Toast.makeText(
                        this@ProductsActivity, "Something went wrong $t",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            })


        val showBasket = findViewById<FloatingActionButton>(R.id.basketButton)
        showBasket.setOnClickListener {
            val intent = Intent(this@ProductsActivity, ShoppingCartActivity::class.java)
            startActivity(intent)
        }


    }

}
