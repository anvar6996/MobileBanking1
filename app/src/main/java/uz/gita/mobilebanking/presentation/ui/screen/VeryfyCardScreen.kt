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
import uz.gita.mobilebanking.data.requests.card.VerifyCardRequest
import uz.gita.mobilebanking.databinding.VeryfyCardScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.SmsVeryfyCardImpl

@AndroidEntryPoint
class VeryfyCardScreen : Fragment(R.layout.veryfy_card_screen) {
    private val bind by viewBinding(VeryfyCardScreenBinding::bind)
    private val viewModel by viewModels<SmsVeryfyCardImpl>()
    private var correctCode = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.sendCode.setOnClickListener {
            viewModel.sendSmsVeryfy(
                VerifyCardRequest(
                    arguments?.getString("code").toString(),
                    bind.codeCardVeryfy.text.toString()
                )
            )
        }
        bind.codeCardVeryfy.addTextChangedListener {
            bind.sendCode.isEnabled = it?.length == 6
        }
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableCardObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableCardObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
    }

    private val disableCardObserver = Observer<Unit> {
        bind.sendCode.isEnabled = false
    }
    private val enableCardObserver = Observer<Unit> {
        bind.sendCode.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private var successObserver = Observer<Unit> {
        findNavController().popBackStack()
    }
}
