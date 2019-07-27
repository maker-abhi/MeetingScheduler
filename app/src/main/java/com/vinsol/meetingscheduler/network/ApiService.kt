package com.vinsol.meetingscheduler.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/api/schedule")
    fun getMeetings(@Query("date") date: String): Call<List<Meeting>>

    @GET("/api/schedule")
    fun getMeetingsRx(@Query("date") date: String): Single<List<Meeting>>
}