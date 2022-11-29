package android.com.baseapp.data.api

import android.com.baseapp.data.api.responeFactory.MyCallAdapterFactory
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    private const val baseUrl = "https://quotable.io/"
    private fun getInstance(): Retrofit {

        val interceptor = HttpLoggingInterceptor(PrettyLogger())
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

internal class PrettyLogger : HttpLoggingInterceptor.Logger {
    private val mGson = GsonBuilder().setPrettyPrinting().create()
    private val mJsonParser = JsonParser()
    private val TAG_API = "GSON"
    override fun log(message: String) {
        val trimMessage = message.trim { it <= ' ' }
        if (trimMessage.startsWith("{") && trimMessage.endsWith("}")
            || trimMessage.startsWith("[") && trimMessage.endsWith("]")
        ) {
            try {
                val prettyJson = mGson.toJson(mJsonParser.parse(message))
                Log.i(TAG_API, prettyJson)
            } catch (e: Exception) {
                Log.e(TAG_API,message, e)
            }
        } else {
            Log.i(TAG_API, message)
        }
    }
}