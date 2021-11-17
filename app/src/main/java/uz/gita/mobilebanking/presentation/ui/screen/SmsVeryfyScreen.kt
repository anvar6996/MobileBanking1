package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.requests.autorization.SmsVeryfyRequest
import uz.gita.mobilebanking.databinding.SmsverifyScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.SmsVeryfyViewModelImpl

@AndroidEntryPoint
class SmsVeryfyScreen : Fragment(R.layout.smsverify_screen) {
    private val bind by viewBinding(SmsverifyScreenBinding::bind)
    private val viewModel by viewModels<SmsVeryfyViewModelImpl>()
    private var number: String = ""
    private var correctCode = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            number = it.getString("phoneNumber", "").toString()
        }
        bind.sendCodeSms.setOnClickListener {
            viewModel.sendSmsVeryfy(
                SmsVeryfyRequest(
                    "+998914181578",
                    bind.codeText.text.toString()
                )
            )
        }

        bind.codeText.addTextChangedListener {
            correctCode = it.toString().isNotEmpty() && it.toString().length == 6
            check()
        }
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableRegisterObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableRegisterObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
    }

    private val disableRegisterObserver = Observer<Unit> {
        bind.sendCodeSms.isEnabled = false
    }
    private val enableRegisterObserver = Observer<Unit> {
        bind.sendCodeSms.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val successObserver = Observer<Unit> {
//        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_smsVeryfyScreen_to_mainScreen)
    }

    private fun check() {
        bind.sendCodeSms.isEnabled = correctCode
    }
}