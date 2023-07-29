package ir.miare.core.data


data class LocalException(
    val httpCode: Int = -1,
    val errorMessage: String = "",
    val localCode : Int = -1
) : Exception()