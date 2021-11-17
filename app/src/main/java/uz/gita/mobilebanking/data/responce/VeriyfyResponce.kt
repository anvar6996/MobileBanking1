package uz.gita.mobilebanking.data.responce

data class VeriyfyResponce(
    val data: TokenData
)

data class TokenData(
    val access_token: String,
    val refresh_token: String,
)