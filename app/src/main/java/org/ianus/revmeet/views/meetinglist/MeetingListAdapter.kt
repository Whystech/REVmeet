package org.ianus.revmeet.views.meetinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.ianus.revmeet.databinding.CardMeetingBinding
import org.ianus.revmeet.models.MeetingModel

class MeetingAdapter(
    private val meetings: List<MeetingModel>
) : RecyclerView.Adapter<MeetingAdapter.MeetingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingHolder {
        val binding = CardMeetingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MeetingHolder(binding)
    }

    override fun onBindViewHolder(holder: MeetingHolder, position: Int) {
        holder.bind(meetings[position])
    }

    override fun getItemCount(): Int = meetings.size

    class MeetingHolder(
        private val binding: CardMeetingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meeting: MeetingModel) {
            binding.meetingTitle.text = meeting.title
            binding.meetingDescription.text = meeting.description
            binding.meetingTypeOfLocation.text = meeting.typeOfLocation
            binding.participantCount.text =
                "${meeting.numberOfParticipants} / ${meeting.maxNumberOfParticipants} participants"
        }
    }
}