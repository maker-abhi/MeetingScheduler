package com.vinsol.meetingscheduler.network

import com.google.gson.annotations.SerializedName

class MeetingResponse(
        @SerializedName("start_time")
        val startTime: String,
        @SerializedName("end_time")
        val endTime: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("participants")
        val participants: List<String>)