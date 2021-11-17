package uz.gita.mobilebanking.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.gita.mobilebanking.data.ApiClient


@Module
@InstallIn(SingletonComponent::class)
 class NetworkModule {

    @Provides
    fun getRetrofit(): Retrofit = ApiClient.retrofit
}