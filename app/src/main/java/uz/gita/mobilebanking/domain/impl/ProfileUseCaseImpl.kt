package uz.gita.mobilebanking.domain.impl

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import uz.gita.mobilebanking.data.responce.profile.Data
import uz.gita.mobilebanking.domain.usecase.ProfilelUseCase
import uz.gita.mobilebanking.domain.usecase.repository.ProfileRepository
import java.io.File
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(private var repository: ProfileRepository) : ProfilelUseCase {
    override fun setAvatar(file: File): Flow<Result<Unit>> = repository.setAvatar(file)

    override fun getAvatar(): Flow<Result<ResponseBody>> = repository.getAvatar()

    override fun editProfile(request: RequestProfileEdit): Flow<Result<Data>> = repository.setInfoProfile(request)

    override fun getInfo(): Flow<Result<DataUser>> = repository.getInfo()

}