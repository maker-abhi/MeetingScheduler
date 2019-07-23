package com.vinsol.meetingscheduler.network

import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("/api/schedule")
    fun getPupils(@Query("date") pageNumber: String): Observable<List<MeetingResponse>>
}