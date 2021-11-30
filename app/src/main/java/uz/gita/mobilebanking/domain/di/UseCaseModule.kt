package uz.gita.mobilebanking.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.domain.impl.*
import uz.gita.mobilebanking.domain.usecase.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun getRegisterUseCase(impl: RegistrUseCaseImpl): RegisterUseCase

    @Binds
    @Singleton
    abstract fun getMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    @Singleton
    abstract fun getSmsVeryfyUseCase(impl: SmsVeryfyUseCaseImpl): SmsVeryfyUseCase

    @Binds
    @Singleton
    abstract fun getLogin(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    @Singleton
    abstract fun getAdd(impl: AddCardUseCaseImpl): AddCardUseCase

    @Binds
    @Singleton
    abstract fun getTrasferUseCase(impl: TransferUseCaseImpl): TransferUseCase

    @Binds
    @Singleton
    abstract fun getProfileUseCase(impl: ProfileUseCaseImpl): ProfilelUseCase

}