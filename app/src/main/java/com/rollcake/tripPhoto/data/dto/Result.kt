package com.rollcake.tripPhoto.data.dto

import com.rollcake.tripPhoto.network.TripProperty


/**
 * A sealed class that encapsulates successful outcome with a value of type [T]
 * or a failure with message and statusCode
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: List<TripProperty>?) : Result<T>()
    data class Error(val message: String?, val statusCode: Int? = null) :
        Result<Nothing>()
}