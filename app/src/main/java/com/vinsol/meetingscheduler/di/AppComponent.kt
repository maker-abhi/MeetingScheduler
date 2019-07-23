package com.vinsol.meetingscheduler.di

import android.app.Application
import com.vinsol.meetingscheduler.MeetingSchedulerApplication

import com.vinsol.meetingscheduler.di.module.ActivitiesModule
import com.vinsol.meetingscheduler.di.module.ApplicationModule
import com.vinsol.meetingscheduler.di.module.DataModule
import com.vinsol.meetingscheduler.di.module.ViewModelModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        DataModule::class,
        ViewModelModule::class,
        ActivitiesModule::class]
)
@Singleton
interface AppComponent {

    fun inject(app: MeetingSchedulerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
