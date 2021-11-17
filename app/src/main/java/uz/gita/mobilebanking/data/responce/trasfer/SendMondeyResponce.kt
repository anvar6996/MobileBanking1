package uz.gita.mobilebanking.data.responce.trasfer

data class SendMondeyResponce(
    val data: DataMoney
)

data class DataMoney(
    val amount: Float,
    val receiver: Int,
    val sender: Int,
    val fee: Float,
    val id: Int,
    val time: Long,
    val status: Int
)
