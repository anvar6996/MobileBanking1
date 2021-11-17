package uz.gita.mobilebanking.data.requests.autorization

data class SmsVeryfyRequest constructor(val phone: String, val code: String)