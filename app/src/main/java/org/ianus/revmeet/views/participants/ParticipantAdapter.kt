package org.ianus.revmeet.views.participants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.ianus.revmeet.databinding.CardParticipantBinding
import org.ianus.revmeet.models.UserModel

class ParticipantAdapter(
    private val users: List<UserModel>,
    private val onUserClick: (UserModel) -> Unit
) : RecyclerView.Adapter<ParticipantAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = CardParticipantBinding.inflate(
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
        private val binding: CardParticipantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel, onUserClick: (UserModel) -> Unit) {
            binding.displayName.text = user.displayName.ifBlank { user.username }
            binding.email.text = user.email
            binding.root.setOnClickListener {
                onUserClick(user)
            }
        }
    }
}