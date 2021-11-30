package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.domain.usecase.ProfilelUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.ProfileScreenViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModelImpl @Inject constructor(private val case: ProfilelUseCase) : ProfileScreenViewModel, ViewModel() {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override val setAvatarLiveData = MutableLiveData<Unit>()
    override val getAvatarLiveData = MutableLiveData<File>()
    override val editProfileLiveData = MutableLiveData<Unit>()
    override val getInfoLiveData = MutableLiveData<DataUser>()


    override fun setAvatar(file: File) {
        if (!isConnected()){
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.value = true
            case.setAvatar(file).onEach {
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

    override fun getAvatar() {
        if (!isConnected()){
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.value = true
            case.getAvatar().onEach {
                progressLiveData.value = false
                enableLoginLiveData.value = Unit
                it.onFailure { th ->
                    errorLivaData.value = th.message
                }
                it.onSuccess { mes ->
                    successLiveData.value = mes.toString()
                    getAvatarLiveData.postValue(mes)
                }

            }.launchIn(viewModelScope)
        }
    }

    override fun editProfile(request: RequestProfileEdit) {
        if (!isConnected()){
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            case.editProfile(request).onEach {
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

    override fun getInfo() {
        if (!isConnected()){
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            case.getInfo().onEach {
                progressLiveData.value = false
                enableLoginLiveData.value = Unit
                it.onFailure { th ->
                    errorLivaData.value = th.message
                }
                it.onSuccess { mes ->
                    successLiveData.value = mes.toString()
                    getInfoLiveData.postValue(mes)
                }

            }.launchIn(viewModelScope)
        }
    }
}