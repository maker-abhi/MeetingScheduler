package com.vinsol.meetingscheduler.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vinsol.meetingscheduler.extensions.compareOnlyTimeTo
import com.vinsol.meetingscheduler.extensions.formatDate
import com.vinsol.meetingscheduler.network.Meeting
import com.vinsol.meetingscheduler.repository.IMeetingsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class MeetingsViewModel @Inject constructor(private val meetingRepository: IMeetingsRepository) : ViewModel() {

    val calendar: Calendar = Calendar.getInstance()

    var startTime: Calendar = Calendar.getInstance()
    var endTime: Calendar = Calendar.getInstance()

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

    fun checkIfSlotAvailable(): Boolean {
        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()
        val meetings = meetingsLiveData.value
        meetings?.forEach { meeting ->
            val startTimeList = meeting.startTime.split(":").map { it.toInt() }
            startCalendar.set(Calendar.HOUR_OF_DAY, startTimeList.first())
            startCalendar.set(Calendar.MINUTE, startTimeList.last())

            val endTimeList = meeting.endTime.split(":").map { it.toInt() }
            endCalendar.set(Calendar.HOUR_OF_DAY, endTimeList.first())
            endCalendar.set(Calendar.MINUTE, endTimeList.last())

            if (startTime.compareOnlyTimeTo(startCalendar) >=0 &&
                startTime.compareOnlyTimeTo(endCalendar) < 0) {
                return false
            }
            if (endTime.compareOnlyTimeTo(endCalendar) <=0 &&
                endTime.compareOnlyTimeTo(startCalendar) > 0) {
                return false
            }
        }
        return true
    }
}