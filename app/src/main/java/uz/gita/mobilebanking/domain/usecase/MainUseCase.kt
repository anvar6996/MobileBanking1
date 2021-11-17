package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.responce.CardData

interface MainUseCase {
    fun getToken(): String

    //    fun userReset(request: Restes) : Flow<Result<Unit>>
    fun userLogout(): Flow<Result<Unit>>

    /*fun cardAdd(request: AddCardRequest) : Flow<Result<Unit>>
    fun cardVerify(request: VerifyCardRequest) : Flow<Result<Unit>>
    fun cardEdit(request: EditCardRequest) : Flow<Result<Unit>>*/
    fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>>
    fun cardGet(): Flow<Result<List<CardData>>>
}