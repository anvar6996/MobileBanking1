package uz.gita.mobilebanking.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.gita.mobilebanking.data.requests.card.*
import uz.gita.mobilebanking.data.responce.AllCardResponse
import uz.gita.mobilebanking.data.responce.card.CardResponce
import uz.gita.mobilebanking.data.responce.card.ResponseOwnerById
import uz.gita.mobilebanking.data.responce.card.ResponseOwnerByPan

interface CardApi {

    @POST("/api/v1/card/add-card")
    suspend fun add(@Body request: AddCardRequest): Response<CardResponce>

    @POST("/api/v1/card/verify")
    suspend fun verify(@Body request: VerifyCardRequest): Response<CardResponce>

    @POST("/api/v1/card/edit-card")
    suspend fun edit(@Body request: EditCardRequest): Response<CardResponce>

    @POST("/api/v1/card/delete-card")
    suspend fun delete(@Body request: DeleteCardRequest): Response<CardResponce>


    @GET("/api/v1/card/all")
    suspend fun getAllCard(): Response<AllCardResponse>


    @GET("/api/v1/card/owner-by-pan")
    suspend fun getOwnerByPan(@Body data: RequesByPanCard): Response<ResponseOwnerByPan>

    @GET("/api/v1/card/owner-by-id")
    suspend fun getOwnerById(@Body data: RequestOwnerById): Response<ResponseOwnerById>
}