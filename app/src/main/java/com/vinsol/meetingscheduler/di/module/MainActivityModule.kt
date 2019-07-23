package com.vinsol.meetingscheduler.di.module

import com.vinsol.meetingscheduler.di.scope.FragmentScope
import com.vinsol.meetingscheduler.fragment.MeetingListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    fun contributeMeetingListFragment(): MeetingListFragment
}
