package android.com.baseapp.data.repository

import android.com.baseapp.data.api.ApiService
import android.com.baseapp.data.api.respone.Resource
import android.com.baseapp.data.model.QuoteList
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor (private val apiService: ApiService) {

    suspend fun getQuotes(): Resource<QuoteList> {
        return apiService.getQuotes()
    }
}