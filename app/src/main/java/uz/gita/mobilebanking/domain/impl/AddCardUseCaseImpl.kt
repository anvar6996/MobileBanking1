package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.requests.card.EditCardRequest
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest
import uz.gita.mobilebanking.domain.usecase.AddCardUseCase
import uz.gita.mobilebanking.domain.usecase.repository.CardRepository
import javax.inject.Inject

class AddCardUseCaseImpl @Inject constructor(private val repositoryCard: CardRepository) : AddCardUseCase {

    override fun addCard(card: AddCardRequest): Flow<Result<Unit>> = repositoryCard.addCard(card)
    override fun editCard(card: EditCardRequest): Flow<Result<Unit>> = repositoryCard.editCard(card)
    override fun deleteCard(card: DeleteCardRequest): Flow<Result<Unit>> = repositoryCard.deleteCard(card)
    override fun veryfy(card: VerifyCardRequest): Flow<Result<Unit>> = repositoryCard.cardVerify(card)

}