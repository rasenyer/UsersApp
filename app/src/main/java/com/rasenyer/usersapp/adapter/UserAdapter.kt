package com.rasenyer.usersapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.rasenyer.usersapp.R
import com.rasenyer.usersapp.model.User
import com.rasenyer.usersapp.databinding.ItemUserBinding

class UserAdapter(private val onItemClicked: (User) -> Unit) : ListAdapter<User, UserAdapter.UserViewHolder>(DiffCallback) {

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldUser: User, newUser: User): Boolean {
                return oldUser.id == newUser.id
            }

            override fun areContentsTheSame(oldUser: User, newUser: User): Boolean {
                return oldUser == newUser
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val userViewHolder = UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from( parent.context), parent, false))

        userViewHolder.itemView.setOnClickListener {
            val position = userViewHolder.adapterPosition
            onItemClicked(getItem(position))
        }

        return userViewHolder

    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, position: Int) {
        userViewHolder.bind(getItem(position))
    }

    class UserViewHolder(private var binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(user: User) {

            binding.mProfilePicture.load(user.profilePicture){
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_profile_picture)
                crossfade(true)
                crossfade(400)
            }

            binding.mTextViewUsername.text = user.username
            binding.mTextViewName.text = user.name

        }

    }

}
