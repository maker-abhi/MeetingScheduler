package com.vinsol.meetingscheduler.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.vinsol.meetingscheduler.di.ViewModelKey
import com.vinsol.meetingscheduler.MeetingsViewModel
import com.vinsol.meetingscheduler.di.ViewModelFactory
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
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
