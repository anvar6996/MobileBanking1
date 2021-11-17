package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.domain.usecase.LoginUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: LoginUseCase) : ViewModel(),
    LoginScreenViewModel {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()

    override fun send(data: AuthorizationRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        useCase.login(data).onEach {
            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onSuccess { mess ->
                successLiveData.value = mess.toString()
            }
            it.onFailure { throweble ->
                errorLivaData.value = throweble.message
            }
        }.launchIn(viewModelScope)
    }
}