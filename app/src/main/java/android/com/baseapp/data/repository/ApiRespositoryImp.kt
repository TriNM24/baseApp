package android.com.baseapp.data.repository

import android.com.baseapp.data.api.ApiService
import android.com.baseapp.data.api.dataResponse.QuoteList
import retrofit2.Response
import javax.inject.Inject

class ApiRespositoryImp @Inject constructor (private val apiService: ApiService) : ApiRepository {

    override suspend fun getQuotes(): Response<QuoteList> {
        return apiService.getQuotes()
    }
}