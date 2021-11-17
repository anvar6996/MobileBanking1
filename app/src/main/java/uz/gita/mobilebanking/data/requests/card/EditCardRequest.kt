package uz.gita.mobilebanking.data.requests.card

data class EditCardRequest(
    val cardNumber: String,
    val newName: String
)