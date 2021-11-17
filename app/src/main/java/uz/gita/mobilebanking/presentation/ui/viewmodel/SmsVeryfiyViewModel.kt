package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest


interface SmsVeryfiyViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<Unit>

    fun sendSmsVeryfy(data: SmsVeryfyRequest)
}