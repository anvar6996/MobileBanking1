package uz.gita.mobilebanking.data.requests.trnsfer_money

import java.io.Serializable

data class TransferFeeRequest(
    val amount: Float,
    val receiverPan: String,
    val sender: Int
) : Serializable
