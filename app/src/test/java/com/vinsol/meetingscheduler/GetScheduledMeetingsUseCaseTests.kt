package com.vinsol.meetingscheduler

import com.vinsol.meetingscheduler.repository.IMeetingsRepository
import com.vinsol.meetingscheduler.usecase.GetScheduledMeetingsUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetScheduledMeetingsUseCaseTests {

    private lateinit var getScheduledMeetingsUseCase: GetScheduledMeetingsUseCase
    private lateinit var meetingsRepository: IMeetingsRepository

    @Before
    fun setup() {
        meetingsRepository = mockk(relaxed = true)
        getScheduledMeetingsUseCase = GetScheduledMeetingsUseCase(meetingsRepository)
    }

    @Test
    fun testRunCallsRepository() {
        val dateString = "27/07/2019"
        getScheduledMeetingsUseCase.run(dateString)
        verify(exactly = 1) { meetingsRepository.getAllMeetingsRx(dateString) }
    }
}