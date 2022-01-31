package com.example.ebazarek_v2.services

import com.example.ebazarek_v2.model.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesService {

    @GET("categories.json")
    fun getCategories(): Call<CategoriesResponse>
}
