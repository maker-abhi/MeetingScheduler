package com.vinsol.meetingscheduler.network

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/api/schedule")
    fun getMeetings(@Query("date") date: String): Call<List<Meeting>>
}