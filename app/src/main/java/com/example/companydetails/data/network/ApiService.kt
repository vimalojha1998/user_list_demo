package com.example.companydetails.data.network

import com.example.companydetails.domain.models.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}