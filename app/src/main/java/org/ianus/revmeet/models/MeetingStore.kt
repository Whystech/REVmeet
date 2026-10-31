package org.ianus.revmeet.models

interface MeetingStore {
    fun findAll(): List<MeetingModel>
    fun findById(id:Long) : MeetingModel?
    fun create(meeting: MeetingModel)
    fun update(meeting: MeetingModel)
    fun delete(meeting: MeetingModel)
}