package uz.gita.mobilebanking.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.responce.CardData

class CardAdapter(val list: ArrayList<CardData>) : RecyclerView.Adapter<CardAdapter.VH>() {
    private var listener: ((Int) -> Unit)? = null
    private var listenerCard: ((CardData) -> Unit)? = null

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        //        private val owner: TextView = view.findViewById(R.id.firstName)
        private val nameUser: TextView = view.findViewById(R.id.nameUser)

        private val expiry: TextView = view.findViewById(R.id.srogCard)
        private val balance: TextView = view.findViewById(R.id.balnce_card)

        //        private val number2: TextView = view.findViewById(R.id.accountNumber2)
        private val cardItem: CardView = view.findViewById(R.id.cardItem)
        private val number4: TextView = view.findViewById(R.id.numberCardItem)

        init {
            itemView.setOnLongClickListener {
                listener?.invoke(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
            cardItem.setOnClickListener {
                listenerCard?.invoke(list[absoluteAdapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val data = list[absoluteAdapterPosition]
//            owner.text = data.owner
            expiry.text = data.exp
            nameUser.text = data.cardName
//            number2.text = data.pan.subSequence(4, 8)
//            number3.text = data.pan.subSequence(8, 12)
            number4.text = data.pan
            balance.text = "${data.balance} sum"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_card2, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    fun setListener(f: (Int) -> Unit) {
        listener = f
    }

    fun setListenerCard(f: (CardData) -> Unit) {
        listenerCard = f
    }
}