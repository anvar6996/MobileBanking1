package uz.gita.mobilebanking.data.requests.trnsfer_money

data class TransferFeeRequest(
    val amount: Int,
    val receiverPan: String,
    val sender: Int
)
