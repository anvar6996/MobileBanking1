package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest
import uz.gita.mobilebanking.domain.usecase.repository.AppRepository
import uz.gita.mobilebanking.domain.usecase.RegisterUseCase
import javax.inject.Inject

class RegistrUseCaseImpl @Inject constructor(private val repository: AppRepository) : RegisterUseCase {
    override fun userRegister(request: RegisterRequest): Flow<Result<Unit>> = repository.resgiter(request)
}