package com.vinsol.meetingscheduler

import com.vinsol.meetingscheduler.network.ApiService
import com.vinsol.meetingscheduler.network.Meeting
import com.vinsol.meetingscheduler.repository.IMeetingsRepository
import retrofit2.Call

class MeetingsRepository(private val service: ApiService) : IMeetingsRepository {

    override fun getAllMeetings(date: String): Call<List<Meeting>> {
        return service.getMeetings(date)
    }
}