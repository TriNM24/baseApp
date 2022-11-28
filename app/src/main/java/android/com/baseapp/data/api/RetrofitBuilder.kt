package android.com.baseapp.data.api

import android.com.baseapp.data.api.responeFactory.MyCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val baseUrl = "https://quotable.io/"
    private fun getInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(MyCallAdapterFactory())
            .client(client)
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
    val apiService: ApiService = getInstance().create(ApiService::class.java)
}