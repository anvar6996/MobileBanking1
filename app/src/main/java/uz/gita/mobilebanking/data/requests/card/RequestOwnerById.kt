package uz.gita.mobilebanking.data.requests.card

import com.google.gson.annotations.SerializedName

data class RequestOwnerById(

	@field:SerializedName("id")
	val id: String
)
