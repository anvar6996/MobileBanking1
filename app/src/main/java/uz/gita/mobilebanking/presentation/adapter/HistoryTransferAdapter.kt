package uz.gita.mobilebanking.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.TransferItemBinding

class HistoryTransferAdapter() : LoadStateAdapter<HistoryTransferAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when (loadState) {
            is LoadState.NotLoading -> error("Not supported")
            LoadState.Loading -> LoadItem(LayoutInflater.from(parent.context).inflate(R.layout.transfer_item, parent, false))
            is LoadState.Error -> ErrorItem(LayoutInflater.from(parent.context).inflate(R.layout.transfer_item, parent, false))
        }
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(load: LoadState)
    }

    inner class LoadItem(view: View) : ItemViewHolder(view) {
        private val bind by viewBinding(TransferItemBinding::bind)
        override fun bind(load: LoadState) {

        }
    }

    inner class ErrorItem(view: View) : ItemViewHolder(view) {
        private val bind by viewBinding(TransferItemBinding::bind)
        override fun bind(load: LoadState) {

        }
    }

    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    private companion object {
        private const val ERROR = 1
        private const val PROGRESS = 1
    }
}