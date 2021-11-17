package uz.gita.mobilebanking.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.gita.mobilebanking.data.api.AuthApi
import uz.gita.mobilebanking.data.api.CardApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun getCardApi(retrofit: Retrofit): CardApi = retrofit.create(CardApi::class.java)

}