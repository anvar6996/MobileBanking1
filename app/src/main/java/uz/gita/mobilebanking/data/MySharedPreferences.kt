package uz.gita.mobilebanking.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.mobilebanking.data.enum.StartScreenEnum
import uz.gita.mobilebanking.presentation.utils.startScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPreferences @Inject constructor(@ApplicationContext context: Context) {
    companion object {
        private lateinit var instance: MySharedPreferences

        fun init(context: Context) {
            instance = MySharedPreferences(context)
        }

        fun getPref(): MySharedPreferences = instance
    }

    private val pref = context.getSharedPreferences("Mobile bank", Context.MODE_PRIVATE)

    var accessToken: String
        set(value) {
            pref.edit().putString("accessToken", value).apply()
        }
        get() = pref.getString("accessToken", "")!!

    var refreshTokent: String
        set(value) {
            pref.edit().putString("refreshTokent", value).apply()
        }
        get() = pref.getString("refreshTokent", "")!!

    var firtstName: String
        set(value) = pref.edit().putString("firtstName", value).apply()
        get() = pref.getString("firtstName", "")!!

    var lastName: String
        set(value) = pref.edit().putString("lastName", value).apply()
        get() = pref.getString("lastName", "")!!

    var phoneNumber: String
        set(value) = pref.edit().putString("phoneNumber", value).apply()
        get() = pref.getString("phoneNumber", "")!!

    var startScreen: StartScreenEnum
        set(value) = pref.edit().putString("startScreen", value.name).apply()
        get() = pref.getString("startScreen", StartScreenEnum.LOGIN.name)!!.startScreen()

    var smsCode: String
        set(value) = pref.edit().putString("smsCode", value).apply()
        get() = pref.getString("smsCode", "")!!

    var controllRegisteget: Boolean
        get() = pref.getBoolean("controllRegister", false)
        set(value) = pref.edit().putBoolean("controllRegister", value).apply()

}