package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest

interface LoginUseCase {

    fun login(request: AuthorizationRequest): Flow<Result<Unit>>
}