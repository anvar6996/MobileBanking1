package uz.gita.mobilebanking.domain.usecase.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.TransferApi
import uz.gita.mobilebanking.data.datasource.HistoryDataSource
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse
import uz.gita.mobilebanking.domain.usecase.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val pref: MySharedPreferences,
    val api: TransferApi
) : HistoryRepository {
    private val config = PagingConfig(10)
    override fun getHistoryPagingData(scope: CoroutineScope): Flow<PagingData<MoneyTransferResponse.HistoryData>> = Pager(config) {
        HistoryDataSource(api, pref)
    }.flow.cachedIn(scope)
}
