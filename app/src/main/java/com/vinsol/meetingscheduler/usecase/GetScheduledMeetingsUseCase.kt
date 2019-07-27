package com.vinsol.meetingscheduler.usecase

import com.vinsol.meetingscheduler.network.Meeting
import com.vinsol.meetingscheduler.repository.IMeetingsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetScheduledMeetingsUseCase @Inject constructor(private val meetingRepository: IMeetingsRepository) {
    fun run(dateString: String): Single<List<Meeting>> = meetingRepository.getAllMeetingsRx(dateString)
}