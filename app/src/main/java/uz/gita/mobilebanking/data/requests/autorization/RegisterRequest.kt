package uz.gita.mobilebanking.data.requests.autorization

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @field:SerializedName("firstName")
    val firstName: String? = null,

    @field:SerializedName("lastName")
    val lastName: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("status")
    val status: String? = "0"
)
