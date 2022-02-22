package com.example.resumebuilder.utils

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

class Resource<T> private constructor(val status: Status, val data: T?, val msg: String? = null) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(msg: String, errorData: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                errorData,
                msg
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data
            )
        }
    }
}