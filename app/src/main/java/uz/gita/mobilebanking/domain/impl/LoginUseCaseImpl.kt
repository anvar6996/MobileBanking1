package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.domain.usecase.repository.AppRepository
import uz.gita.mobilebanking.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val repository: AppRepository) : LoginUseCase {
    override fun login(request: AuthorizationRequest): Flow<Result<Unit>> = repository.userLogin(request)
}