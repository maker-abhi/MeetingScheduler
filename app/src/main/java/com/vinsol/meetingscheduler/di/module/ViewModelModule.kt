package com.vinsol.meetingscheduler.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vinsol.meetingscheduler.di.ViewModelKey
import com.vinsol.meetingscheduler.viewmodel.MeetingsViewModel
import com.vinsol.meetingscheduler.di.ViewModelFactory
import com.vinsol.meetingscheduler.viewmodel.ScheduleMeetingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MeetingsViewModel::class)
    abstract fun bindMeetingsViewModel(viewModel: MeetingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleMeetingViewModel::class)
    abstract fun bindScheduleMeetingViewModel(viewModel: ScheduleMeetingViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
