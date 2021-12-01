package uz.gita.mobilebanking.domain.usecase

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.data.responce.profile.Data
import java.io.File

interface ProfilelUseCase {
    fun setAvatar(file: File): Flow<Result<Unit>>
    fun getAvatar(): Flow<Result<ResponseBody>>
    fun editProfile(request: RequestProfileEdit): Flow<Result<Data>>
    fun getInfo(): Flow<Result<DataUser>>

}