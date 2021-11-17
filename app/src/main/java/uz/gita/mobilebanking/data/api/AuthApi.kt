package uz.gita.mobilebanking.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.data.responce.BasicResponce
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest
import uz.gita.mobilebanking.data.responce.VeriyfyResponce

interface AuthApi {
    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: AuthorizationRequest): Response<BasicResponce>

    @POST("/api/v1/auth/logout")
    suspend fun logout(@Header("token") token: String): Call<BasicResponce>

    @POST("/api/v1/auth/reset")
    suspend fun reset(@Body data: AuthorizationRequest): Call<BasicResponce>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body data: RegisterRequest): Response<BasicResponce>

    @POST("/api/v1/auth/verify")
    suspend fun veryfyCode(@Body data: SmsVeryfyRequest): Response<VeriyfyResponce>
}