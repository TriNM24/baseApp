package android.com.baseapp.di

import android.com.baseapp.data.repository.ApiRepository
import android.com.baseapp.data.repository.ApiRespositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class BindInterfaceModule {
    @Binds
    abstract fun bindApiRepository(
        apiRepository: ApiRespositoryImp
    ) : ApiRepository
}
