package uz.gita.mobilebanking.data.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.data.requests.profile_user.ResponseProfileInfo
import uz.gita.mobilebanking.data.responce.profile.ResponseAvatar
import uz.gita.mobilebanking.data.responce.profile.ResponseProfileEdit

interface Profile {

    @Multipart
    @POST("/api/v1/profile/avatar")
    suspend fun setAvatar(@Header("token") token: String, @Part part: MultipartBody.Part): Response<ResponseAvatar>

    @GET("/api/v1/profile/avatar")
    suspend fun getAvatar(@Header("token") token: String): Response<MultipartBody.Part>

    @PUT("/api/v1/profile")
    suspend fun editProfile(@Header("token") token: String, @Body data: RequestProfileEdit): Response<ResponseProfileEdit>

    @GET("/api/v1/profile/info")
    suspend fun profileInfo(@Header("token") token: String): Response<ResponseProfileInfo>


}