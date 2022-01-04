package com.example.photos.data

import com.example.entities.ApiResult
import com.example.entities.Photo
import com.example.photos.di.IoDispatcher
import com.example.photos.network.PlaceholderApiService
import com.example.photos.network.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitDataSource @Inject constructor(
    private val apiService: PlaceholderApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ApiDataSource {

    override fun getAllPhotos(): Flow<ApiResult<List<Photo>>> = safeLoadingFlow(
        mapper = { it.toEntity() }
    ) { apiService.getPhotos() }

    override fun getPhotoDetail(id: Int): Flow<ApiResult<Photo>> = safeLoadingFlow(
        mapper = { it.toEntity() }
    ) { apiService.getPhoto(id) }

    private fun <T, R> safeLoadingFlow(
        mapper: (T) -> R?,
        callApi: suspend () -> Response<T>
    ): Flow<ApiResult<R>> = flow<ApiResult<R>> {
        emit(ApiResult.Loading)
        emit(safeApiCall(mapper, callApi))
    }.flowOn(ioDispatcher)

    private suspend fun <T, R> safeApiCall(
        mapper: ((T) -> R?),
        apiCall: suspend () -> Response<T>
    ): ApiResult<R> = withContext(ioDispatcher) {
        try {
            val response = apiCall()
            val apiResponse = response.toApiResult(mapper)
            apiResponse
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): ApiResult<T> =
        ApiResult.Error("Api call failed $errorMessage")


    private fun <T, R> Response<T>.toApiResult(mapper: ((T) -> (R?))): ApiResult<R> {
        return if (isSuccessful) {
            when (val body = body()) {
                null -> error("null")
                else -> {
                    mapper(body)?.let { mappedBody ->
                        ApiResult.Success(mappedBody)
                    } ?: error("null")
                }
            }
        } else {
            val errorBodyString = errorBody()?.string()
            error("$errorBodyString")
        }
    }
}
