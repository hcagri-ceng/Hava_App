package com.kampplus.hava.core.common

/**
 * CP4 İstenenleri 1: Asenkron Veri Çağrısı Sonuç Sarmalayıcısı.
 */
sealed interface AppResult<out T> {
    data class Success<out T>(val data: T) : AppResult<T>
    data class Error(val error: AppError) : AppResult<Nothing>
}

sealed interface AppError {
    data object NetworkError : AppError
    data object NotFoundError : AppError
    data class UnknownError(val message: String? = null) : AppError
}
