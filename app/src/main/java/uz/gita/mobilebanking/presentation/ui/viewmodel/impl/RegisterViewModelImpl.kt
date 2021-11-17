package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest
import uz.gita.mobilebanking.domain.usecase.RegisterUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.RegisterViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(var usecase: RegisterUseCase) : RegisterViewModel,
    ViewModel() {
    override val enableLoginLiveData = MutableLiveData<Unit>()

    override val disableLoginLiveData = MutableLiveData<Unit>()

    override val progressLiveData = MutableLiveData<Boolean>()

    override val errorLivaData = MutableLiveData<String>()

    override val successLiveData = MutableLiveData<String>()


    override fun regiter(data: RegisterRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        usecase.userRegister(data).onEach {
            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onFailure { th ->
                errorLivaData.value = th.message
            }
            it.onSuccess { mes ->
                successLiveData.value = mes.toString()
            }
        }.launchIn(viewModelScope)
    }
}