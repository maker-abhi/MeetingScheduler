package com.vinsol.meetingscheduler.model

import com.vinsol.meetingscheduler.model.Status.ERROR
import com.vinsol.meetingscheduler.model.Status.LOADING
import com.vinsol.meetingscheduler.model.Status.SUCCESS
import com.vinsol.meetingscheduler.model.Status.INIT

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val errorCode: Int?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(errorCode: Int? = 999, data: T? = null): Resource<T> {
            return Resource(ERROR, data, errorCode)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
        fun <T> init(): Resource<T> {
            return Resource(INIT, null, null)
        }
    }
}
