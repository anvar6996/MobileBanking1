package uz.gita.mobilebanking.domain.usecase.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse

interface HistoryRepository {
    fun getHistoryPagingData(scope: CoroutineScope): Flow<PagingData<MoneyTransferResponse.HistoryData>>
}