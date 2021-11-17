package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.responce.CardData
import uz.gita.mobilebanking.domain.impl.MainUseCaseImpl
import uz.gita.mobilebanking.presentation.ui.viewmodel.MainViewModel
import uz.gita.mobilebanking.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val usecase: MainUseCaseImpl) : ViewModel(), MainViewModel {
    override val errorLivaData = MutableLiveData<String>()
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val successLiveData = MutableLiveData<List<CardData>>()
    override val progressLiveData = MutableLiveData<Boolean>()

    override fun getAllCard() {
        if (!isConnected()){
            errorLivaData.value = "Internetga ulanish bilan bog'liq muammolar bor"
            return
        }
        progressLiveData.value = true
        usecase.cardGet().onEach {
            progressLiveData.value = false
            it.onFailure { throwable->
                errorLivaData.value = throwable.message
            }
            it.onSuccess {list->
                successLiveData.value = list
            }
        }.launchIn(viewModelScope)
    }

}