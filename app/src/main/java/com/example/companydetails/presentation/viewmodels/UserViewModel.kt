package com.example.companydetails.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.companydetails.domain.models.User
import com.example.companydetails.domain.usecases.GetUsersUseCase
import com.example.companydetails.presentation.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> get() = _userState

    fun fetchUsers() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            try {
                val usersList = getUsersUseCase.getUsers()
                if (usersList.isEmpty()) {
                    _userState.value = UserState.Error("No users found")
                } else {
                    _userState.value = UserState.Success(usersList)
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "An error occurred")
            }
        }
    }
}