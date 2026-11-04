package org.ianus.revmeet.views.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.ianus.revmeet.databinding.CardUserBinding
import org.ianus.revmeet.models.UserModel

class UserAdapter(
    private val users: List<UserModel>
) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = CardUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    class UserHolder(
        private val binding: CardUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel) {
            binding.userName.text = user.username
            binding.userEmail.text = user.email
        }
    }
}