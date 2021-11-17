package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.responce.CardData
import uz.gita.mobilebanking.domain.usecase.MainUseCase
import uz.gita.mobilebanking.domain.usecase.repository.CardRepository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(private val repository: CardRepository) : MainUseCase {
    override fun getToken(): String {
        TODO("Not yet implemented")
    }

    override fun userLogout(): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun cardDelete(request: DeleteCardRequest): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }

    override fun cardGet(): Flow<Result<List<CardData>>> = repository.getAllCard()
}