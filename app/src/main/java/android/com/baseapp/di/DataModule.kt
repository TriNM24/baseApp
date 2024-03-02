package android.com.baseapp.di

import android.com.baseapp.R
import android.com.baseapp.data.api.ApiService
import android.com.baseapp.data.api.PrettyLogger
import android.com.baseapp.utils.Constants.API_KEY
import android.com.baseapp.utils.Constants.API_KEY_VALUE
import android.com.baseapp.utils.Constants.NETWORK_NO_CONNECT
import android.com.baseapp.utils.Constants.NETWORK_TIME_OUT
import android.com.baseapp.utils.Constants.NETWORK_UNKNOWN_ERROR
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val prettyLogInterceptor = HttpLoggingInterceptor(PrettyLogger())
        prettyLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return prettyLogInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        prettyLogInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builderOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        //add interceptor for pretty log
        builderOkHttpClient.addInterceptor(prettyLogInterceptor)
        builderOkHttpClient.addInterceptor(ChuckerInterceptor(context))
        builderOkHttpClient.addInterceptor(Interceptor { chain ->
            val build = chain.request().newBuilder().addHeader(
                API_KEY,
                API_KEY_VALUE
            ).build()
            try {
                chain.proceed(build)
            }catch (e: java.lang.Exception){
                when (e) {
                    is UnknownHostException -> {
                        okhttp3.Response.Builder()
                            .code(NETWORK_NO_CONNECT) // Whatever code
                            .body("".toResponseBody(null)) // Whatever body
                            .protocol(Protocol.HTTP_2)
                            .message(context.getString(R.string.error_not_internet))
                            .request(chain.request())
                            .build()
                    }
                    is java.net.SocketTimeoutException -> {
                        okhttp3.Response.Builder()
                            .code(NETWORK_TIME_OUT) // Whatever code
                            .body("".toResponseBody(null)) // Whatever body
                            .protocol(Protocol.HTTP_2)
                            .message(context.getString(R.string.connect_time_out))
                            .request(chain.request())
                            .build()
                    }
                    else -> {
                        okhttp3.Response.Builder()
                            .code(NETWORK_UNKNOWN_ERROR) // Whatever code
                            .body("".toResponseBody(null)) // Whatever body
                            .protocol(Protocol.HTTP_2)
                            .message(context.getString(R.string.message_unknown_error))
                            .request(chain.request())
                            .build()
                    }
                }
            }

        })

        return builderOkHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, @ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder().baseUrl(context.getString(R.string.api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}