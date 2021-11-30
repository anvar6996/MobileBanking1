package uz.gita.mobilebanking.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.usecase.repository.AppRepository
import uz.gita.mobilebanking.domain.usecase.repository.CardRepository
import uz.gita.mobilebanking.domain.usecase.repository.ProfileRepository
import uz.gita.mobilebanking.domain.usecase.repository.TransferRepository
import uz.gita.mobilebanking.domain.usecase.repositoryimpl.AppRepositoryImpl
import uz.gita.mobilebanking.domain.usecase.repositoryimpl.CardRepositoryImpl
import uz.gita.mobilebanking.domain.usecase.repositoryimpl.ProfileRepositoryImpl
import uz.gita.mobilebanking.domain.usecase.repositoryimpl.TransferRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {
    @Binds
    @Singleton
    abstract fun getAppRepository(impl: AppRepositoryImpl): AppRepository

    @Binds
    @Singleton
    abstract fun getCardRepository(impl: CardRepositoryImpl): CardRepository

    @Binds
    @Singleton
    abstract fun getTransferRepository(impl: TransferRepositoryImpl): TransferRepository

    @Binds
    @Singleton
    abstract fun getProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

}