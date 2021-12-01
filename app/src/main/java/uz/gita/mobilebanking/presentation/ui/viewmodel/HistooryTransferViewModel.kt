package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse


interface HistooryTransferViewModel {
    val getListoryliveData: LiveData<PagingData<MoneyTransferResponse.HistoryData>>
}