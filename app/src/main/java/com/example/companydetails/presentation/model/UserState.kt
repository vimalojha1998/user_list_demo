package com.example.companydetails.presentation.model

import com.example.companydetails.domain.models.User

sealed class UserState {
    data object Loading : UserState()
    data class Success(val users: List<User>) : UserState()
    data class Error(val message: String) : UserState()
}