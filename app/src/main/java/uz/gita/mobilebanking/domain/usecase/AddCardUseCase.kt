package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.requests.card.EditCardRequest
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest

interface AddCardUseCase {
    fun addCard(card: AddCardRequest): Flow<Result<Unit>>
    fun editCard(card: EditCardRequest): Flow<Result<Unit>>
    fun deleteCard(card: DeleteCardRequest): Flow<Result<Unit>>
    fun veryfy(card: VerifyCardRequest): Flow<Result<Unit>>
}