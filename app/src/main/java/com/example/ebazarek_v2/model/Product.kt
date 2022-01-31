package com.example.ebazarek_v2.model

import com.squareup.moshi.Json
import java.util.*


data class Product(

    var id: Int = 0,
    var title: String,
    var description: String,
    var price: Double,
    @Json(name = "created_at") val createdAt: Date = Date(),
    @Json(name = "updated_at") var updatedAt: Date?
) {
    override fun toString(): String {
        return title
    }
}
