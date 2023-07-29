package ir.miare.core.data.source.entity.error

data class ErrorEntity(
    val errorEntities: List<ErrorMessagesEntity>
)

data class ErrorMessagesEntity(
    val code: String,
    val message: String
)