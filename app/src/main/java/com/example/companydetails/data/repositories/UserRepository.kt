package com.example.companydetails.data.repositories

import com.example.companydetails.data.network.ApiService
import com.example.companydetails.domain.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers() : List<User> = apiService.getUsers()
}