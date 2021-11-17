package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest

interface SmsVeryfyUseCase {
    fun userVerify(request: SmsVeryfyRequest): Flow<Result<Unit>>

    fun setScreen()
}