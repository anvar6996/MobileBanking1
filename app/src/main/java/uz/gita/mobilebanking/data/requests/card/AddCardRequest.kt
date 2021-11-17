package uz.gita.mobilebanking.data.requests.card

data class AddCardRequest(
    val pan: String,
    val exp: String,
    val cardName: String
)