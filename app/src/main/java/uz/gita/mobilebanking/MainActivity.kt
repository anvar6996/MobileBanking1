package uz.gita.mobilebanking

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.data.others.StaticValues

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        StaticValues.eventLiveData.observe(this, observerToken)
    }

    private val observerToken = Observer<Unit> {
        val host = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val graph = host.navController.navInflater.inflate(R.navigation.nav_res)
        host.navController.graph = graph
        host.navController.navigate(R.id.loginScreen)
    }
}