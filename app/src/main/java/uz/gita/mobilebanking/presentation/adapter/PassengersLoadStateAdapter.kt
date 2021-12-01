package uz.gita.mobilebanking.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobilebanking.databinding.TransferItemBinding

class PassengersLoadStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<PassengersLoadStateAdapter.PassengersLoadStateViewHolder>() {

    inner class PassengersLoadStateViewHolder(
        private val binding: TransferItemBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.transferName.text = loadState.error.localizedMessage
            }
            binding.progressbar.visible(loadState is LoadState.Loading)
            binding.buttonRetry.visible(loadState is LoadState.Error)
            binding.textError.visible(loadState is LoadState.Error)
            binding.buttonRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onBindViewHolder(
        holder: PassengersLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = PassengersLoadStateViewHolder(
        TransferItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
        retry
    )
}