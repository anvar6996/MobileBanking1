package uz.gita.mobilebanking.domain.usecase.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.requests.card.EditCardRequest
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest
import uz.gita.mobilebanking.data.responce.CardData


interface CardRepository {
    fun addCard(card: AddCardRequest): Flow<Result<Unit>>
    fun editCard(card: EditCardRequest): Flow<Result<Unit>>
    fun deleteCard(card: DeleteCardRequest): Flow<Result<Unit>>
    fun cardVerify(card: VerifyCardRequest): Flow<Result<Unit>>
    fun getAllCard(): Flow<Result<List<CardData>>>
}