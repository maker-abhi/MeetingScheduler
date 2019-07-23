package com.vinsol.meetingscheduler.repository

import com.vinsol.meetingscheduler.network.Meeting
import retrofit2.Call

interface IMeetingsRepository {
    fun getAllMeetings(date: String): Call<List<Meeting>>
}