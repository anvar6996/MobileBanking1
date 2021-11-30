package uz.gita.mobilebanking.data.requests.profile_user

data class ResponseProfileInfo(
    val data: DataUser
)

data class DataUser(

    val firstName: String,//name
    val lastName: String,//last name
    val password: String,// PIN
    val phone: String//phone number
)
