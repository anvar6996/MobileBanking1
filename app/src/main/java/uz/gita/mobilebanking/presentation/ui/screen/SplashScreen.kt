package uz.gita.mobilebanking.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.SplashViewModel

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.splash_screen) {
    private val viewModel by viewModels<SplashViewModel>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(this, observer)

    }

    private var observer = Observer<Unit> {
        val pref = MySharedPreferences.getPref()
        if (pref.controllRegisteget) {
            findNavController().navigate(R.id.action_splashScreen_to_mainScreen)
        } else {
            findNavController().navigate(R.id.action_splashScreen_to_authorizationScreen)
        }
    }
}