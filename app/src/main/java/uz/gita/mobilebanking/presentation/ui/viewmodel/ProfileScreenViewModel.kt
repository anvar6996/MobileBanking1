package uz.gita.mobilebanking.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking.data.requests.profile_user.DataUser
import uz.gita.mobilebanking.data.requests.profile_user.RequestProfileEdit
import java.io.File


interface ProfileScreenViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>

    val setAvatarLiveData: LiveData<Unit>
    val getAvatarLiveData: LiveData<File>
    val editProfileLiveData: LiveData<Unit>
    val getInfoLiveData: LiveData<DataUser>


    fun setAvatar(file: File)
    fun getAvatar()
    fun editProfile(request: RequestProfileEdit)
    fun getInfo()
}