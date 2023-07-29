package ir.miare.core.data


sealed class ResponseState<out T>(
    val data: T? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T? = null) : ResponseState<T>(data)
    class Error<T>(exception: Exception, data: T? = null) : ResponseState<T>(data, exception)
    object Loading : ResponseState<Nothing>()
}