package uz.gita.mobilebanking.domain.usecase.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.CardApi
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.requests.card.EditCardRequest
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest
import uz.gita.mobilebanking.data.responce.CardData
import uz.gita.mobilebanking.data.responce.card.CardResponce
import uz.gita.mobilebanking.domain.usecase.repository.CardRepository
import uz.gita.mobilebanking.presentation.utils.timber
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val api: CardApi, private val pref: MySharedPreferences) : CardRepository {

    override fun addCard(card: AddCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.add(card)))
    }

    override fun editCard(card: EditCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.edit(card)))
    }

    override fun deleteCard(card: DeleteCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.delete(card)))
    }

    override fun cardVerify(card: VerifyCardRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.verify(card)))
    }


    private fun getResult(response: Response<CardResponce>): Result<Unit> {
        return try {
            if (response.code() in 200..299) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            timber(e.message.toString())
            Result.failure(Throwable("Sever bilan muammo bo'ldi"))
        }
    }

    override fun getAllCard(): Flow<Result<List<CardData>>> = flow {
        try {
            val response = api.getAllCard()
            if (response.code() == 200) {
                emit(Result.success(response.body()!!.data.data))
            } else {
                val st = "Serverga ulanishda xatolik bo'ldi"

                emit(Result.failure(Throwable(st)))
            }
        } catch (e: Exception) {

            emit(Result.failure(Throwable("Serverga ulanishda xatolik bo'ldi(Catch)")))
        }
    }.flowOn(Dispatchers.IO)
}