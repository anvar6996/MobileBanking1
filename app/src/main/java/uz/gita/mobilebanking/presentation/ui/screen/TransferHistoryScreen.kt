package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse
import uz.gita.mobilebanking.databinding.HistoryTransferScreenBinding
import uz.gita.mobilebanking.presentation.adapter.HistoryTransferAdapter
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.HistoryTasferViewModelImpl

@AndroidEntryPoint
class TransferHistoryScreen : Fragment(R.layout.history_transfer_screen) {
    private val bind by viewBinding(HistoryTransferScreenBinding::bind)
    private val viewModel by viewModels<HistoryTasferViewModelImpl>()
    private var adapter: HistoryTransferAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryTransferAdapter()
        bind.recyklerPaging.adapter = adapter
        bind.recyklerPaging.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getListoryliveData.observe(viewLifecycleOwner, observerHistory)
    }

    private val observerHistory = Observer<PagingData<MoneyTransferResponse.HistoryData>>
    {

    }
}