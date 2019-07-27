package com.vinsol.meetingscheduler.repository

import com.vinsol.meetingscheduler.network.Meeting
import io.reactivex.Single
import retrofit2.Call

interface IMeetingsRepository {
    fun getAllMeetings(date: String): Call<List<Meeting>>
    fun getAllMeetingsRx(date: String): Single<List<Meeting>>
}