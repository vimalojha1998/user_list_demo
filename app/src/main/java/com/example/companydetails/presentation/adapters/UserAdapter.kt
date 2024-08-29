package com.example.companydetails.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companydetails.databinding.ItemUserBinding
import com.example.companydetails.domain.models.User

class UserAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount(): Int = users.size

    inner class UserViewHolder(private val itemUserBinding:  ItemUserBinding) :
        RecyclerView.ViewHolder(itemUserBinding.root) {
        fun bind(user: User) {
            itemUserBinding.textViewName.text = user.name
            itemUserBinding.textViewEmail.text = user.email
            itemUserBinding.textViewGeo.text = "Lat: ${user.address.geo.lat}, Lng: ${user.address.geo.lng}"
            itemUserBinding.textViewCompany.text = user.company.name
        }
    }

    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
}