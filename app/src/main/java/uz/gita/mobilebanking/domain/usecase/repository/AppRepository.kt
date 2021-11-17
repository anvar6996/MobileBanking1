package uz.gita.mobilebanking.domain.usecase.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.enum.StartScreenEnum
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest

interface AppRepository {
    fun userLogin(authRequest: AuthorizationRequest): Flow<Result<Unit>>
    fun resgiter(request: RegisterRequest): Flow<Result<Unit>>
    fun sendSmsVeryfy(request: SmsVeryfyRequest): Flow<Result<Unit>>
    fun userLogout(token: String): Flow<Result<Unit>>


    fun startScreen(): StartScreenEnum

    fun setStartScreen(screen: StartScreenEnum)

    fun getToken(): String
//    fun userNewPassword(request: NewPasswordRequest) : Flow<Result<Unit>>
//    fun userResend(request: ResendRequest) : Flow<Result<Unit>>
}