package com.vinsol.meetingscheduler

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vinsol.meetingscheduler.extensions.formatDate
import com.vinsol.meetingscheduler.network.Meeting
import com.vinsol.meetingscheduler.repository.IMeetingsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class MeetingsViewModel @Inject constructor(private val meetingRepository: IMeetingsRepository) : ViewModel() {

    val calendar: Calendar = Calendar.getInstance()
    val meetingsLiveData = MutableLiveData<List<Meeting>>()

    init {
        loadMeetings()
    }

    fun loadMeetings() {
        val dateString = calendar.time.formatDate()
        meetingRepository.getAllMeetings(dateString)
            .enqueue(object : Callback<List<Meeting>> {
                override fun onFailure(call: Call<List<Meeting>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Meeting>>, response: Response<List<Meeting>>) {
                    meetingsLiveData.value = response.body()
                }
            })
    }
}