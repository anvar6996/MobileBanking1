package uz.gita.mobilebanking.domain.usecase.repositoryimpl

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import uz.gita.mobilebanking.data.ApiClient
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.AuthApi
import uz.gita.mobilebanking.data.enum.StartScreenEnum
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.data.responce.BasicResponce
import uz.gita.mobilebanking.data.requests.autorization.RegisterRequest
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest
import uz.gita.mobilebanking.domain.usecase.repository.AppRepository
import uz.gita.mobilebanking.presentation.utils.timber
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(private val api: AuthApi, private val pref: MySharedPreferences) : AppRepository {
    private val gson = Gson()

    override fun userLogin(authRequest: AuthorizationRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.login(authRequest)))
    }.flowOn(Dispatchers.IO)


    override fun resgiter(request: RegisterRequest): Flow<Result<Unit>> = flow {
        emit(getResult(api.register(request)))
    }.flowOn(Dispatchers.IO)

    override fun sendSmsVeryfy(request: SmsVeryfyRequest): Flow<Result<Unit>> = flow {
        val api = ApiClient.retrofit.create(AuthApi::class.java)
        try {
            val response = api.veryfyCode(request)
            if (response.code() == 200) {
                response.body()?.let {
                    pref.accessToken = it.data.access_token
                    pref.refreshTokent = it.data.refresh_token
                }
                emit(Result.success(Unit))
            } else {
                emit(Result.failure<Unit>(Throwable(response.errorBody().toString())))
            }
        } catch (e: Exception) {
            emit(Result.failure<Unit>(Throwable("Serverga ulanishda xatolik bo'ldi")))
        }
    }.flowOn(Dispatchers.IO)

    override fun userLogout(token: String): Flow<Result<Unit>> = flow {
//        emit(getResult(api.logout(token)))
    }

    override fun startScreen(): StartScreenEnum {
        TODO("Not yet implemented")
    }

    override fun setStartScreen(screen: StartScreenEnum) {
        TODO("Not yet implemented")
    }

    override fun getToken(): String {
        TODO("Not yet implemented")
    }

    private fun getResult(response: Response<BasicResponce>): Result<Unit> {
        return try {
            if (response.code() in 200..299) {
                Result.success(Unit)
            } else {
                Result.failure(Throwable(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            timber(e.message.toString())
            Result.failure(Throwable("Sever bilan muammo bo'ldi"))
        }
    }


}