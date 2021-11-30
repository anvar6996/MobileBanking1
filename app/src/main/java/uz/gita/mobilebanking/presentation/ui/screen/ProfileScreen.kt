package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ProfileScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.ProfileScreenViewModelImpl

@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.profile_screen) {
    private val bind by viewBinding(ProfileScreenBinding::bind)
    private val viewmodel by viewModels<ProfileScreenViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.save.setOnClickListener {

        }
    }

}