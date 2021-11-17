package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest

interface LoginScreenViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val progressLiveData: LiveData<Boolean>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<String>

    fun send(data: AuthorizationRequest)
}