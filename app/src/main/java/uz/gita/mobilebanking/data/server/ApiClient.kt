package uz.gita.mobilebanking.data.server

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobilebanking.BuildConfig.LOGGING
import uz.gita.mobilebanking.app.App
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.responce.VeriyfyResponce
import uz.gita.mobilebanking.presentation.utils.timber

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://b35d-82-215-107-245.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(getHttpClient()).build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addLogging()
            .addInterceptor(refreshInterceptor())
            .addInterceptor(tokenInterceptor())
            .build()
    }

}

private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    if (LOGGING) {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                timber(message, "HTTP")
            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
        addNetworkInterceptor(ChuckInterceptor(App.instance))
    }
    return this
}

fun tokenInterceptor() = Interceptor {
    val request = it.request()
    val pref = MySharedPreferences.getPref()
    val newRequest = request.newBuilder().removeHeader("token").addHeader("token", pref.accessToken).build()
    val response = it.proceed(newRequest)
    response
}

fun refreshInterceptor() = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)
    if (response.code == 401) {
        response.close()
        val pref = MySharedPreferences.getPref()
        val data = JSONObject()
        data.put("phone", "+998914181578")
        val body =
            data.toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val requestRefresh = request.newBuilder()
            .addHeader("refresh_token", pref.refreshTokent)
            .method("POST", body)
            .url("http://b35d-82-215-107-245.ngrok.io/api/v1/auth/refresh")
            .build()

        val responseRefresh = chain.proceed(requestRefresh)
        if (responseRefresh.code == 401) {
            return@Interceptor responseRefresh
        }

        if (responseRefresh.code == 200) {
            responseRefresh.body?.let {
                val data = Gson().fromJson(it.string(), VeriyfyResponce::class.java)
                pref.accessToken = data.data.access_token
                pref.refreshTokent = data.data.refresh_token
            }
            responseRefresh.close()
            val requestTwo = request.newBuilder()
                .removeHeader("token")
                .addHeader("token", pref.accessToken)
                .build()
            return@Interceptor chain.proceed(requestTwo)
        }
    }
    return@Interceptor response
}