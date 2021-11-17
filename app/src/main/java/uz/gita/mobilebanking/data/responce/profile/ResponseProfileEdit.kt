package uz.gita.mobilebanking.data.responce.profile

data class ResponseProfileEdit(
	val data: Data
)

data class Data(

	val firstName: String,
	val lastName: String,
	val password: String,
	val phone: String
)
