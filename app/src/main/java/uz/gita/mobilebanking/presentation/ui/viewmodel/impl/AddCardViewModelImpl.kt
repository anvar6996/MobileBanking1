package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.domain.usecase.AddCardUseCase
import uz.gita.mobilebanking.presentation.ui.viewmodel.AddCardViewModel
import javax.inject.Inject

@HiltViewModel
class
AddCardViewModelImpl @Inject constructor(private val case: AddCardUseCase) : AddCardViewModel, ViewModel() {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override fun addCard(card: AddCardRequest) {
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        case.addCard(card).onEach {
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