package com.vinsol.meetingscheduler.di.module

import com.vinsol.meetingscheduler.MeetingsActivity
import com.vinsol.meetingscheduler.di.scope.ActivityScope

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesModule {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    @ActivityScope
    fun contributeMeetingsActivity(): MeetingsActivity
}
