package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest

interface RegisterUseCase {
    fun userRegister(request: RegisterRequest): Flow<Result<Unit>>
}