package uz.gita.mobilebanking.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.responce.trasfer.MoneyTransferResponse
import uz.gita.mobilebanking.databinding.TransferItemBinding


class HistoryAdapter : PagingDataAdapter<MoneyTransferResponse.HistoryData, HistoryAdapter.HistoryViewHolder>(
    MyDiffUtil
) {
    private var itemListener: ((MoneyTransferResponse.HistoryData) -> Unit)? = null

    object MyDiffUtil : DiffUtil.ItemCallback<MoneyTransferResponse.HistoryData>() {
        override fun areItemsTheSame(
            oldItem: MoneyTransferResponse.HistoryData,
            newItem: MoneyTransferResponse.HistoryData
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MoneyTransferResponse.HistoryData,
            newItem: MoneyTransferResponse.HistoryData
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bind by viewBinding(TransferItemBinding::bind)

        init {
            bind.item.setOnClickListener {
                itemListener?.invoke(getItem(absoluteAdapterPosition)!!)
            }
        }

        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            data?.let {
                if (it.status == 0) {
                    bind.item.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.purple_500
                        )
                    )
                } else {
                    bind.item.setBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.purple_200
                        )
                    )
                }
                bind.cardCode.text = data.sender.toString()
                bind.time.text = data.time.toString()
                bind.trensferAmount.text = data.amount.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transfer_item, parent, false)
        )

    fun setItemListener(block: (MoneyTransferResponse.HistoryData) -> Unit) {
        itemListener = block
    }
}