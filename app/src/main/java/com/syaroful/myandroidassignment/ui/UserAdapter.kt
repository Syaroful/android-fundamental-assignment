package com.syaroful.myandroidassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.databinding.ItemRowUserBinding

class UserAdapter(private val users: List<ItemsItem>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.apply {
                tvUsername.text = user.login
                tvUrl.text = user.url
                Glide.with(itemView.context).load(user.avatarUrl).into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(users[position])
    }
}