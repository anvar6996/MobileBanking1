package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.requests.card.RequesByPanCard
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee
import uz.gita.mobilebanking.data.responce.trasfer.SendMondeyResponce
import uz.gita.mobilebanking.domain.usecase.TransferUseCase
import uz.gita.mobilebanking.domain.usecase.repository.TransferRepository
import javax.inject.Inject

class TransferUseCaseImpl @Inject constructor(private var repository: TransferRepository) : TransferUseCase {

    override fun sendMoney(card: RequestMoneyTransfer): Flow<Result<SendMondeyResponce>> = repository.sendMoney(card)

    override fun transferFee(data: TransferFeeRequest): Flow<Result<ResponseTransferFee>> = repository.transferFee(data)

    override fun getName(data: RequesByPanCard): Flow<Result<String>> = repository.getName(data)

}