package android.com.baseapp.di

import android.com.baseapp.data.api.ApiService
import android.com.baseapp.data.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitBuilder.apiService
    }
}