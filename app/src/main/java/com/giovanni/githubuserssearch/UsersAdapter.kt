package com.giovanni.githubuserssearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UsersAdapter (private val listUsers: List<ItemsItem>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_ghusers,viewGroup,false))

    override fun getItemCount() = listUsers.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val Users= listUsers[position]
        viewHolder.tvItem.text = Users.login
        Glide.with(viewHolder.itemView.context)
            .load(Users.avatarUrl)
            .circleCrop()
            .into(viewHolder.imgPhoto)

        viewHolder.itemView.setOnClickListener {
            val intentDetail = Intent(viewHolder.itemView.context, DetailUserActivity::class.java)
            intentDetail.putExtra(DetailUserActivity.EXTRA_USER, Users.login)
            viewHolder.itemView.context.startActivity(intentDetail)
        }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tv_name)
        val imgPhoto: ImageView = view.findViewById((R.id.img_item_photo))
    }
}
