package uz.gita.mobilebanking.domain.usecase.repositoryimpl

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.api.Profile
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.data.responce.BasicResponce
import uz.gita.mobilebanking.domain.usecase.repository.ProfileRepository
import java.io.File
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(private val api: Profile, private val pref: MySharedPreferences) : ProfileRepository {

    @SuppressLint("NewApi")
    override fun setInfoProfile(register: RequestProfileEdit): Flow<Result<DataUser>> = flow {
        val responce = api.editProfile(register)
        when {
            responce.isSuccessful -> emit(Result.success<DataUser>(responce.body()?.data as DataUser))
            responce.errorBody() != null -> {
                val errorbody = responce.errorBody()!!.string()
                emit(Result.failure<DataUser>(Throwable(errorbody)))
            }
            else -> {
                emit(Result.failure<DataUser>(Throwable("Serverda ulanishda xato")))
            }
        }
    }

    override fun setAvatar(part: MultipartBody.Part): Flow<Result<Unit>> = flow {
        val responce = api.setAvatar(part)
        when {
            responce.isSuccessful -> emit(Result.success(Unit))
            responce.errorBody() != null -> {
                val errorbody = responce.errorBody()!!.string() as BasicResponce
                emit(Result.failure<Unit>(Throwable(errorbody.message)))
            }
            else -> {
                emit(Result.failure<Unit>(Throwable("Serverda ulanishda xato")))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getAvatar(): Flow<Result<File>> = flow {
        val responce = api.getAvatar()
        when {
            responce.isSuccessful -> emit(Result.success<File>(responce.body()!!))
            responce.errorBody() != null -> {
                val errorbody = responce.errorBody()!!.string() as BasicResponce
                emit(Result.failure<File>(Throwable(errorbody.message)))
            }
            else -> {
                emit(Result.failure<File>(Throwable("Sever bilan muammo bo'ldi")))
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getInfo(): Flow<Result<DataUser>> = flow {
        val responce = api.profileInfo()
        when {
            responce.isSuccessful -> emit(Result.success(responce.body()!!.data))
            responce.errorBody() != null -> {
                val errorbody = responce.errorBody()!!.string() as BasicResponce
                emit(Result.failure<DataUser>(Throwable(errorbody.message)))
            }
            else -> {
                emit(Result.failure<DataUser>(Throwable("Sever bilan muammo bo'ldi")))
            }
        }
    }.flowOn(Dispatchers.IO)

}