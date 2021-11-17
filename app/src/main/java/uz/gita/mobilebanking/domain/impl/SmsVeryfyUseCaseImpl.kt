package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.enum.StartScreenEnum
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest
import uz.gita.mobilebanking.domain.usecase.repository.AppRepository
import uz.gita.mobilebanking.domain.usecase.SmsVeryfyUseCase
import javax.inject.Inject

class SmsVeryfyUseCaseImpl @Inject constructor(var repository: AppRepository) : SmsVeryfyUseCase {
    override fun userVerify(request: SmsVeryfyRequest): Flow<Result<Unit>> = repository.sendSmsVeryfy(request)

    override fun setScreen() {
        repository.setStartScreen(StartScreenEnum.MAIN)
    }
}