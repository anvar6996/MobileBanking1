package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.card.RequesByPanCard
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee
import uz.gita.mobilebanking.domain.usecase.TransferUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.TransferCardViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class TransferCardViewModelImpl @Inject constructor(private val case: TransferUseCase) : TransferCardViewModel, ViewModel() {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val successNameLiveData = MutableLiveData<String>()
    override val successTransferFeeLiveData = MutableLiveData<ResponseTransferFee>()

    override fun sendMoney(card: RequestMoneyTransfer) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        progressLiveData.value = true
        enableLoginLiveData.value = Unit
        case.sendMoney(card).onEach {
            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onSuccess {
                successLiveData.value = Unit
            }
            it.onFailure { throwable ->
                errorLivaData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun transferFee(data: TransferFeeRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }

        progressLiveData.value = true
        case.transferFee(data).onEach {
            progressLiveData.value = false
            it.onSuccess { resulst ->
                successTransferFeeLiveData.value = resulst
            }
            it.onFailure { throwable ->
                errorLivaData.value = throwable.message
            }
        }.launchIn(viewModelScope)
    }

    override fun getName(data: RequesByPanCard) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        progressLiveData.value = true
        case.getName(data).onEach {
            progressLiveData.value = false
            it.onSuccess { name ->
                successNameLiveData.value = name
            }
            it.onFailure { throwable ->
                errorLivaData.value = throwable.message
            }
        }.catch {
            errorLivaData.value = "Server bilan bog'liq muammo bor"
        }.launchIn(viewModelScope)
    }

}