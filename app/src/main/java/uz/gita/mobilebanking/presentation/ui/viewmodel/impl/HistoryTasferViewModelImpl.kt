package uz.gita.mobilebanking.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse
import uz.gita.mobilebanking.domain.usecase.repository.HistoryRepository
import uz.gita.mobilebanking.presentation.ui.viewmodel.HistooryTransferViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryTasferViewModelImpl @Inject constructor(private val historyRepository: HistoryRepository) : ViewModel(), HistooryTransferViewModel {
    override val getListoryliveData = MutableLiveData<PagingData<MoneyTransferResponse.HistoryData>>()

    init {
        historyRepository.getHistoryPagingData(viewModelScope).onEach {
            getListoryliveData.postValue(it)
        }
    }
}