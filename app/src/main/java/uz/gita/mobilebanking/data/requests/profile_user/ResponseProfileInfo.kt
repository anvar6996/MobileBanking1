package uz.gita.mobilebanking.data.requests.profile_user

data class ResponseProfileInfo(
    val data: DataUser
)

data class DataUser(

    val firstName: String,
    val lastName: String,
    val password: String,
    val phone: String
)
