package org.ianus.revmeet.views.participants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.ianus.revmeet.databinding.CardParticipantdetailBinding
import org.ianus.revmeet.models.UserModel

class ListParticipantAdapter(
    private val users: List<UserModel>,
    private val onUserClick: (UserModel) -> Unit
) : RecyclerView.Adapter<ListParticipantAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = CardParticipantdetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position], onUserClick)
    }

    override fun getItemCount(): Int = users.size

    class UserHolder(
        private val binding: CardParticipantdetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel, onUserClick: (UserModel) -> Unit) {
            binding.displayName.text = user.displayName.ifBlank { user.username }
            binding.email.text = user.email
            binding.username.text = user.username
            binding.bio.text = user.bio
            binding.root.setOnClickListener {
                onUserClick(user)
            }
        }
    }
}