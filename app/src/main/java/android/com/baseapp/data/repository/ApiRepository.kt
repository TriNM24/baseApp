package android.com.baseapp.data.repository

import android.com.baseapp.data.api.dataResponse.QuoteList
import retrofit2.Response

interface ApiRepository {
    suspend fun getQuotes() : Response<QuoteList>
}