package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest
import uz.gita.mobilebanking.domain.usecase.AddCardUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.SmsVeryfyCard
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class SmsVeryfyCardImpl @Inject constructor(private val useCase: AddCardUseCase) : ViewModel(), SmsVeryfyCard {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<Unit>()

    override fun sendSmsVeryfy(data: VerifyCardRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        disableLoginLiveData.value = Unit
        useCase.veryfy(data).onEach {
            enableLoginLiveData.value = Unit
            it.onFailure { exc ->
                errorLivaData.value = exc.message
            }
            it.onSuccess {
                successLiveData.value = Unit
            }
        }.launchIn(viewModelScope)

    }

}