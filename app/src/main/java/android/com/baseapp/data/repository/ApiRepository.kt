package android.com.baseapp.data.repository

import android.com.baseapp.data.model.QuoteList
import retrofit2.Response

interface ApiRepository {
    suspend fun getQuotes() : Response<QuoteList>
}