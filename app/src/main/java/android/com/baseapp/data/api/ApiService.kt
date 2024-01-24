package android.com.baseapp.data.api

import android.com.baseapp.data.api.dataResponse.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}