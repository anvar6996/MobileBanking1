package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest
import uz.gita.mobilebanking.domain.usecase.SmsVeryfyUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.SmsVeryfiyViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class SmsVeryfyViewModelImpl @Inject constructor(private var useCase: SmsVeryfyUseCase) : ViewModel(),
    SmsVeryfiyViewModel {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<Unit>()

    override fun sendSmsVeryfy(data: SmsVeryfyRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        disableLoginLiveData.value = Unit
        useCase.userVerify(data).onEach {
            enableLoginLiveData.value = Unit
            it.onFailure { exc ->
                errorLivaData.value = exc.message
            }
            it.onSuccess { mess ->
                successLiveData.value = Unit
            }
        }.launchIn(viewModelScope)
    }
}