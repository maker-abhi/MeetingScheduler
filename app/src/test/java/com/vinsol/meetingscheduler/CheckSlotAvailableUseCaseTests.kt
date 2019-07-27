package com.vinsol.meetingscheduler

import com.vinsol.meetingscheduler.network.Meeting
import com.vinsol.meetingscheduler.usecase.CheckSlotAvailableUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class CheckSlotAvailableUseCaseTests {

    private lateinit var checkSlotAvailableUseCase: CheckSlotAvailableUseCase

    private val scheduledMeetings = listOf(
        Meeting(
            "11:30", "12:00", "", emptyList()
        )
    )

    @Before
    fun setup() {
        checkSlotAvailableUseCase = CheckSlotAvailableUseCase()
    }

    @Test
    fun checkSlotAvailable_endTimeBeforeAllScheduledMeetingsEndTime() {
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 10)
        startTime.set(Calendar.MINUTE, 30)
        val endTime = Calendar.getInstance()
        endTime.set(Calendar.HOUR_OF_DAY, 11)
        endTime.set(Calendar.MINUTE, 30)

        val args = CheckSlotAvailableUseCase.Arguments(scheduledMeetings, startTime, endTime)
        val result = checkSlotAvailableUseCase.run(args)
        Assert.assertTrue(result)
    }

    @Test
    fun checkSlotAvailable_startTimeAfterAllScheduledMeetingsEndTime() {
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 12)
        startTime.set(Calendar.MINUTE, 0)
        val endTime = Calendar.getInstance()
        endTime.set(Calendar.HOUR_OF_DAY, 12)
        endTime.set(Calendar.MINUTE, 30)

        val args = CheckSlotAvailableUseCase.Arguments(scheduledMeetings, startTime, endTime)
        val result = checkSlotAvailableUseCase.run(args)
        Assert.assertTrue(result)
    }

    @Test
    fun checkSlotUnavailable_startTimeFallsBetweenAScheduledMeeting() {
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 11)
        startTime.set(Calendar.MINUTE, 45)
        val endTime = Calendar.getInstance()
        endTime.set(Calendar.HOUR_OF_DAY, 12)
        endTime.set(Calendar.MINUTE, 30)

        val args = CheckSlotAvailableUseCase.Arguments(scheduledMeetings, startTime, endTime)
        val result = checkSlotAvailableUseCase.run(args)
        Assert.assertFalse(result)
    }

    @Test
    fun checkSlotUnavailable_endTimeFallsBetweenAScheduledMeeting() {
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 11)
        startTime.set(Calendar.MINUTE, 0)
        val endTime = Calendar.getInstance()
        endTime.set(Calendar.HOUR_OF_DAY, 12)
        endTime.set(Calendar.MINUTE, 0)

        val args = CheckSlotAvailableUseCase.Arguments(scheduledMeetings, startTime, endTime)
        val result = checkSlotAvailableUseCase.run(args)
        Assert.assertFalse(result)
    }

    @Test
    fun checkSlotUnavailable_bothStartEndTimeFallBetweenAScheduledMeeting() {
        val startTime = Calendar.getInstance()
        startTime.set(Calendar.HOUR_OF_DAY, 11)
        startTime.set(Calendar.MINUTE, 40)
        val endTime = Calendar.getInstance()
        endTime.set(Calendar.HOUR_OF_DAY, 11)
        endTime.set(Calendar.MINUTE, 50)

        val args = CheckSlotAvailableUseCase.Arguments(scheduledMeetings, startTime, endTime)
        val result = checkSlotAvailableUseCase.run(args)
        Assert.assertFalse(result)
    }
}