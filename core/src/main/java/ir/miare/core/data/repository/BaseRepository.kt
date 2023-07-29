package ir.miare.core.data.repository

import android.util.Log
import ir.miare.core.data.LocalException
import ir.miare.core.data.ResponseState
import ir.miare.core.data.source.entity.error.ErrorMessagesEntity
import kotlinx.coroutines.flow.FlowCollector
import retrofit2.Response
import java.lang.Exception
import javax.inject.Singleton

@Singleton
open class BaseRepository {

    object ErrorMessages {
        val   errorMessages = ArrayList<ErrorMessagesEntity>()
    }


    private val TAG = BaseRepository::class.java.name
    protected fun <T> responseMapper(response: Response<T>): ResponseState<T> {
        Log.d(TAG, "responseMapper: $ ${response.raw().request.url}")
        return if (response.isSuccessful) {
            Log.d(TAG, "responseMapper:  Successful")
            ResponseState.Success(response.body()!!)
        } else {
            Log.e(TAG, "responseMapper:  NOT Successful")

            ResponseState.Error(Exception("Error"))
        }
    }

    protected fun <T, R> responseMapper(
        response: Response<T>,
        data: R? = null
    ): ResponseState<R> {

        Log.d(TAG, "responseMapper: Request Url : ${response.raw().request.url.encodedPath}")

        return if (response.isSuccessful) {
            Log.d(TAG, "responseMapper:  Successful")

            data?.let { ResponseState.Success(it) } ?: kotlin.run { ResponseState.Success() }
        } else {
            Log.e(TAG, "responseMapper:  NOT Successful")

            ResponseState.Error(Exception("Error"))
        }
    }


    private fun getErrorMessage(code: String): String {
        Log.d("TAG", "getErrorMessage: error messages : ${ErrorMessages.errorMessages?.size}")
        Log.d("TAG", "getErrorMessage: code : " + code)
        val message = "خطای نا مشخص"

        for (error in ErrorMessages.errorMessages) {
            Log.i("TAG", "getErrorMessage: list code : ${error.code}")
            if (error.code == code) {
                Log.i(TAG, "getErrorMessage: if code equals to error code")
                return error.message
            }
        }

        Log.i(TAG, "getErrorMessage: ")



        return message
    }


    suspend fun <T> FlowCollector<ResponseState<T>>.emitRemoteResult(value: ResponseState<T>) {
        emit(value)
    }

    suspend fun FlowCollector<ResponseState.Loading>.emitLoading() {
        emit(ResponseState.Loading)

    }

    suspend fun <T> FlowCollector<ResponseState<T>>.emitError(localException: LocalException) {
        emit(ResponseState.Error(localException, data = null))

    }


}