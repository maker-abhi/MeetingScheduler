package com.vinsol.meetingscheduler.di.module

import com.google.gson.Gson
import com.vinsol.meetingscheduler.repository.MeetingsRepository
import com.vinsol.meetingscheduler.network.ApiService
import com.vinsol.meetingscheduler.repository.IMeetingsRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideMeetingsRepository(apiService: ApiService): IMeetingsRepository {
        return MeetingsRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
