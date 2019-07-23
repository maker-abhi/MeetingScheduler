package com.vinsol.meetingscheduler.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.vinsol.meetingscheduler.network.ApiService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

//    @Provides
//    @Singleton
//    fun provideSharedPreferences(application: Application): SharedPreferences {
//        return application.getSharedPreferences("beercraft", Context.MODE_PRIVATE)
//    }
//
//    @Provides
//    @Singleton
//    fun providePupilRepository(apiService: ApiService, pupilDao: PupilDao): IPupilRepository {
//        return PupilRepository(apiService, pupilDao)
//    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
