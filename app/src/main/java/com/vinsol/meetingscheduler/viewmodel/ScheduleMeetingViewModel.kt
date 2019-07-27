package com.vinsol.meetingscheduler.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vinsol.meetingscheduler.usecase.CheckSlotAvailableUseCase
import com.vinsol.meetingscheduler.usecase.GetScheduledMeetingsUseCase
import com.vinsol.meetingscheduler.extensions.formatDate
import com.vinsol.meetingscheduler.model.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ScheduleMeetingViewModel @Inject constructor(
    private val getScheduledMeetingsUseCase: GetScheduledMeetingsUseCase,
    private val checkSlotAvailableUseCase: CheckSlotAvailableUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val calendar: Calendar = Calendar.getInstance()

    var startTime: Calendar = Calendar.getInstance()
    var endTime: Calendar = Calendar.getInstance()

    val slotAvailabilityLiveData = MutableLiveData<Resource<Boolean>>()

    init {
        slotAvailabilityLiveData.value = Resource.init()
    }

    fun checkIfSlotAvailable() {
        val dateString = calendar.time.formatDate()
        compositeDisposable.add(getScheduledMeetingsUseCase.run(dateString)
            .map { meetings ->
                checkSlotAvailableUseCase.run(CheckSlotAvailableUseCase.Arguments(meetings, startTime, endTime))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { availability ->
                slotAvailabilityLiveData.value = Resource.success(availability)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}