package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.RequesByPanCard
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee
import uz.gita.mobilebanking.data.responce.trasfer.SendMondeyResponce

interface TransferUseCase {
    fun sendMoney(card: RequestMoneyTransfer): Flow<Result<SendMondeyResponce>>
    fun transferFee(data: TransferFeeRequest): Flow<Result<ResponseTransferFee>>
    fun getName(data: RequesByPanCard): Flow<Result<String>>
}