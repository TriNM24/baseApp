package android.com.baseapp.data.api.respone

sealed class ApiResult<T>(
    val data: T? = null,
    val message: ValueErrorResponse? = null,
    val errorCode: String? = null
) {

    class Success<T>(data: T) : ApiResult<T>(data)
    class Loading<T> : ApiResult<T>()
    class Error<T>(errorCode: String?, message: ValueErrorResponse?) : ApiResult<T>(null, message, errorCode)

    override fun toString(): String {
        return when (this) {
            is Loading<T> -> "Loading"
            is Success<*> -> "Success [data=$data]"
            is Error -> "Error [exception=$errorCode][message=$message]"
        }
    }
}