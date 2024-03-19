package com.syaroful.myandroidassignment.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syaroful.myandroidassignment.data.response.ItemsItem
import com.syaroful.myandroidassignment.databinding.ItemRowUserBinding
import com.syaroful.myandroidassignment.ui.detail.UserDetailActivity

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var users: List<ItemsItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<ItemsItem>) {
        users = newList
        notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            binding.apply {
                tvUsername.text = user.login
                tvUrl.text = user.url
                Glide.with(itemView.context).load(user.avatarUrl).into(imageView)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.EXTRA_USERNAME, user.login)
                itemView.context.startActivity(intent)
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
