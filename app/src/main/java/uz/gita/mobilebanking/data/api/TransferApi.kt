package uz.gita.mobilebanking.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.requests.trnsfer_money.TransferFeeRequest
import uz.gita.mobilebanking.data.responce.trasfer.*

interface TransferApi {

    @POST("/api/v1/money-transfer/send-money")//
    suspend fun sendMoney(@Body data: RequestMoneyTransfer): Response<SendMondeyResponce>

    @POST("/api/v1/money-transfer/fee")// 1 karta  2 qiymat  3 id
    suspend fun transferFee(@Body data: TransferFeeRequest): Response<ResponseTransferFee>

    @GET("/api/v1/money-transfer/history")
    suspend fun history(): Response<MoneyTransferResponse>

    @GET("/api/v1/money-transfer/incomes")
    suspend fun incomes(): Response<ResponseIncomes>

    @GET("/api/v1/money-transfer/outcomes")
    suspend fun outcomes(): Response<ResponseOutcomes>
}