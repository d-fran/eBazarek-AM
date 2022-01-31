package com.example.ebazarek_v2.services

import com.example.ebazarek_v2.model.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {

    @GET("categories/{id}/products.json")
    //Call<List<Category>> categoriesList(@Path("id") int id)
    fun getProducts(@Path("id") id: Int): Call<ProductsResponse>

}