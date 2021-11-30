package uz.gita.mobilebanking.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.TransferApi
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse

//class HistoryDataSource(val api: TransferApi, val pref: MySharedPreferences) : PagingSource<Int, MoneyTransferResponse.HistoryData>() {
//    override fun getRefreshKey(state: PagingState<Int, MoneyTransferResponse.HistoryData>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoneyTransferResponse.HistoryData> {
//        return try {
//            val nextPageNumber = params.key ?: 0
//            val pageSize = 10
//            val response = api.history(nextPageNumber, pageSize)
//
//            LoadResult.Page(
//                data = response.body()!!.data.data,
//                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
//                nextKey = if (nextPageNumber < (response.body()!!.data.totalCount / response.body()!!.data.pageSize) + 1)
//                    nextPageNumber + 1 else null
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(Throwable("Ulanishda xatolik bo'ldi"))
//        }
//    }
//
//}