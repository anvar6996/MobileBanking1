package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.requests.card.RequesByPanCard
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee

interface TransferCardViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>
    val successNameLiveData: LiveData<String>
    val successTransferFeeLiveData: LiveData<ResponseTransferFee>


    fun sendMoney(card: RequestMoneyTransfer)
    fun transferFee(data: TransferFeeRequest)
    fun getName(data: RequesByPanCard)
}