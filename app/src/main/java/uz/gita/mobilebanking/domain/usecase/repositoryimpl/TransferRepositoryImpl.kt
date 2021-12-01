package uz.gita.mobilebanking.domain.usecase.repositoryimpl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.CardApi
import uz.gita.mobilebanking.data.api.TransferApi
import uz.gita.mobilebanking.data.requests.card.RequesByPanCard
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee
import uz.gita.mobilebanking.data.responce.trasfer.SendMondeyResponce
import uz.gita.mobilebanking.data.server.ApiClient
import uz.gita.mobilebanking.domain.usecase.repository.TransferRepository
import javax.inject.Inject


class TransferRepositoryImpl @Inject constructor(private val api: TransferApi, private val pref: MySharedPreferences) : TransferRepository {
    private val gson = Gson()

    override fun sendMoney(data: RequestMoneyTransfer): Flow<Result<SendMondeyResponce>> = flow {
        val response = api.sendMoney(data)
        try {
            if (response.code() in 200..299) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Throwable(response.errorBody().toString())))
            }
        } catch (e: Exception) {
            emit(Result.failure(Throwable("Serverga ulanishda xatolik bo'ldi(catch)")))
        }
    }.flowOn(Dispatchers.IO)

    override fun transferFee(data: TransferFeeRequest): Flow<Result<ResponseTransferFee>> = flow {

        try {
            val response = api.transferFee(data)
            if (response.code() in 200..299) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Throwable(response.errorBody().toString())))
            }
        } catch (e: Exception) {
            emit(Result.failure(Throwable("Serverga ulanishda xatolik bo'ldi(catch)")))
        }

    }.flowOn(Dispatchers.IO)


    override fun getName(data: RequesByPanCard): Flow<Result<String>> = flow {
        val api2 = ApiClient.retrofit.create(CardApi::class.java)
        val response = api2.getOwnerByPan(data)
        when {
            response.isSuccessful -> {
                emit(Result.success(response.body()!!.data.fio))
            }
            response.errorBody() != null -> {
                val errorBody = response.errorBody()!!.toString()
                emit(Result.failure(Throwable(errorBody)))
            }
            else -> {
                emit(Result.failure(Throwable("Serverga ulanishda xatolik bo'ldi")))
            }
        }
    }.flowOn(Dispatchers.IO)

}