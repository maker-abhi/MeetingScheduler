package com.vinsol.meetingscheduler.usecase

import com.vinsol.meetingscheduler.extensions.compareOnlyTimeTo
import com.vinsol.meetingscheduler.network.Meeting
import java.util.*
import javax.inject.Inject

class CheckSlotAvailableUseCase @Inject constructor() {
    fun run(args: Arguments): Boolean {
        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()
        args.meetings?.forEach { meeting ->
            val startTimeList = meeting.startTime.split(":").map { it.toInt() }
            startCalendar.set(Calendar.HOUR_OF_DAY, startTimeList.first())
            startCalendar.set(Calendar.MINUTE, startTimeList.last())

            val endTimeList = meeting.endTime.split(":").map { it.toInt() }
            endCalendar.set(Calendar.HOUR_OF_DAY, endTimeList.first())
            endCalendar.set(Calendar.MINUTE, endTimeList.last())

            if (args.startTime.compareOnlyTimeTo(startCalendar) >= 0 &&
                args.startTime.compareOnlyTimeTo(endCalendar) < 0
            ) {
                return false
            }
            if (args.endTime.compareOnlyTimeTo(endCalendar) <= 0 &&
                args.endTime.compareOnlyTimeTo(startCalendar) > 0
            ) {
                return false
            }
            if (args.startTime.compareOnlyTimeTo(startCalendar) <= 0 &&
                args.endTime.compareOnlyTimeTo(endCalendar) >= 0
            ) {
                return false
            }
        }
        return true
    }

    class Arguments(val meetings: List<Meeting>?, val startTime: Calendar, val endTime: Calendar)
}