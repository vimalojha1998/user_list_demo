package com.example.companydetails.domain.usecases

import com.example.companydetails.data.repositories.UserRepository
import com.example.companydetails.domain.models.User
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepo: UserRepository) {

    suspend  fun getUsers(): List<User> {
        return userRepo.getUsers()
    }
}