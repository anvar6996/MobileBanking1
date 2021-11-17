package uz.gita.mobilebanking.data.requests.trnsfer_money


data class RequestMoneyTransfer(
	val amount: Int,
	val receiverPan: String,
	val sender: Int
)
