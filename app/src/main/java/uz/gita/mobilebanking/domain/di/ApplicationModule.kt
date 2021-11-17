package uz.gita.mobilebanking.domain.di

import android.content.Context
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebanking.data.MySharedPreferences


//@Module
//@InstallIn(SingletonComponent::class)
//class ApplicationModule {
//
//    @Provides
//    fun getSharedPreference(@ApplicationContext context: Context): MySharedPreferences = MySharedPreferences.getPref()
//}