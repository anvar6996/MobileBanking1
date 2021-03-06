package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.requests.card.AddCardRequest

interface AddCardViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>
    fun addCard(card: AddCardRequest)
}