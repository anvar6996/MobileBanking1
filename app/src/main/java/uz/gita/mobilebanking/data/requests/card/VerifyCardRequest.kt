package uz.gita.mobilebanking.data.requests.card

data class VerifyCardRequest(
    val pan: String,
    val code: String
)
