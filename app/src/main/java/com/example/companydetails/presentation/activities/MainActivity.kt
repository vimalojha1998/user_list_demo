package com.example.companydetails.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.activity.viewModels
import com.example.companydetails.databinding.ActivityMainBinding
import com.example.companydetails.presentation.adapters.UserAdapter
import com.example.companydetails.presentation.model.UserState
import com.example.companydetails.presentation.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private val userAdapter = UserAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupRecyclerview()
        observeUserList()

        userViewModel.fetchUsers()
    }

    private fun setupRecyclerview(){
        mBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

    private fun observeUserList(){
        userViewModel.userState.observe(this, Observer { state ->
            when(state){
                is UserState.Loading -> {
                    mBinding.progressBar.visibility = android.view.View.VISIBLE
                    mBinding.rvUsers.visibility = android.view.View.GONE
                }
                is UserState.Success -> {
                    mBinding.progressBar.visibility = android.view.View.GONE
                    mBinding.rvUsers.visibility = android.view.View.VISIBLE
                    userAdapter.updateUsers(state.users)
                }
                is UserState.Error -> {
                    mBinding.progressBar.visibility = android.view.View.GONE
                    mBinding.rvUsers.visibility = android.view.View.GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}