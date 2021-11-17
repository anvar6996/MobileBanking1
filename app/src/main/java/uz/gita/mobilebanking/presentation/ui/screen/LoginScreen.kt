package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.requests.autorization.AuthorizationRequest
import uz.gita.mobilebanking.databinding.LoginScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.LoginViewModel
import uz.gita.mobilebanking.presentation.utils.showToast


@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.login_screen) {
    private val bind by viewBinding(LoginScreenBinding::bind)
    private val viewModel: LoginScreenViewModel by viewModels<LoginViewModel>()
    private var boolPassword = false
    private var boolPhoneNumber = false
    private var boolConfirmPassword = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.sendLogin.setOnClickListener {
            viewModel.send(
                AuthorizationRequest(
                    bind.phoneNumber.text.toString(),
                    bind.password.text.toString()
                )
            )
        }
        bind.password.addTextChangedListener {
            boolPassword =
                it?.length!! > 6 && it.toString() == bind.confrimPassword.text.toString()
            check()
        }
        bind.confrimPassword.addTextChangedListener {
            boolConfirmPassword =
                it?.length!! > 6 && it.toString() == bind.password.text.toString()
            check()
        }
        bind.phoneNumber.addTextChangedListener {
            boolPhoneNumber = it?.length == 13 && it.toString().startsWith("+998")
            check()
        }

        viewModel.successLiveData.observe(viewLifecycleOwner, openMainScreenObserver)
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableLoginButtonObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableLoginButtonObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorMessageObserver)
//        viewModel.progressLiveData.observe(viewLifecycleOwner, showProgressObserver)
    }

    private fun check() {
        bind.sendLogin.isEnabled = (boolPassword || boolConfirmPassword) && boolPhoneNumber
    }

    private val disableLoginButtonObserver = Observer<Unit> {
        bind.sendLogin.isEnabled = false
    }

    private val enableLoginButtonObserver = Observer<Unit> {
        bind.sendLogin.isEnabled = true
    }
    private val errorMessageObserver = Observer<String> {
        showToast(it)
    }
    private val hideProgressObserver = Observer<Unit> {
//        bind.progress.hide()
    }
    private val showProgressObserver = Observer<Unit> {
//        bind.progress.show()
    }
    private val openMainScreenObserver = Observer<String> {
        findNavController().navigate(R.id.action_loginScreen_to_smsVeryfyScreen)
    }
}