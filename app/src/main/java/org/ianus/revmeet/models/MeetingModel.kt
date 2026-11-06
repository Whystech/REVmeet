package org.ianus.revmeet.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeetingModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var typeOfLocation : String = "",
                          var maxNumberOfParticipants: Int = 1,
                          var startsAt: Long = 0L,
                          var organizerUserId: Long = 0L,
                          var participantUserIds: ArrayList<Long> = arrayListOf(),
                          var lat : Double = 0.0,
                          var lng: Double = 0.0) : Parcelable {
                          val numberOfParticipants: Int get() = participantUserIds.size
}
