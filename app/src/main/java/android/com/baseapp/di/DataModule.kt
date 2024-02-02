package android.com.baseapp.di

import android.com.baseapp.R
import android.com.baseapp.data.api.ApiService
import android.com.baseapp.data.api.PrettyLogger
import android.com.baseapp.utils.Constants.API_KEY
import android.com.baseapp.utils.Constants.API_KEY_VALUE
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        //add interceptor for pretty log
        builderOkHttpClient.addInterceptor(prettyLogInterceptor)
        builderOkHttpClient.addInterceptor(ChuckerInterceptor(context))
        builderOkHttpClient.networkInterceptors().add(Interceptor { chain ->
            val build = chain.request().newBuilder().addHeader(
                API_KEY,
                API_KEY_VALUE
            ).build()
            chain.proceed(build)
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