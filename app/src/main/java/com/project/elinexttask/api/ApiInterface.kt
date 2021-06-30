package com.project.elinexttask.api

import com.project.elinexttask.api.model.Image
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/images/search")
    suspend fun getImages(
        @Header("x-api-key") header: String,
        @Query("limit") limit: Int
    ): List<Image>
}