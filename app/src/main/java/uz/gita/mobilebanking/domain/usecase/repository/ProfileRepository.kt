package uz.gita.mobilebanking.domain.usecase.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import java.io.File


interface ProfileRepository {
    fun setInfoProfile(register: RequestProfileEdit): Flow<Result<DataUser>>
    fun setAvatar(part: MultipartBody.Part): Flow<Result<Unit>>

    fun getAvatar(): Flow<Result<File>>
    fun getInfo(): Flow<Result<DataUser>>
}