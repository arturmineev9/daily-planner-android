package ru.arturmineev9.dailyplanner.core.common.result

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Error(val error: Throwable, val message: String? = null) : AppResult<Nothing>
}

inline fun <T, R> AppResult<T>.map(transform: (T) -> R): AppResult<R> {
    return when (this) {
        is AppResult.Success -> AppResult.Success(transform(this.data))
        is AppResult.Error -> AppResult.Error(this.error, this.message)
    }
}
