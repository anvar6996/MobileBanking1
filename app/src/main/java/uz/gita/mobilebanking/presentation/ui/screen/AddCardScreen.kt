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
import uz.gita.mobilebanking.data.requests.card.AddCardRequest
import uz.gita.mobilebanking.databinding.AddCardScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.AddCardViewModel
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.AddCardViewModelImpl

@AndroidEntryPoint
class AddCardScreen : Fragment(R.layout.add_card_screen) {
    private val bind by viewBinding(AddCardScreenBinding::bind)
    private val viewModel: AddCardViewModel by viewModels<AddCardViewModelImpl>()
    private var boolCodeText = true
    private var boolDateText = true
    private var boolNameText = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bind.addNow.setOnClickListener {
            viewModel.addCard(AddCardRequest(bind.codeText.text.toString(), bind.dateText.text.toString(), bind.nameText.text.toString()))
        }

        bind.codeText.addTextChangedListener {
            bind.numberCard.text = it
//            if (it.toString().length == 16 && it.toString().substring(4) == "8600") {
//                boolCodeText = true
//            }
            check()
        }

        bind.dateText.addTextChangedListener {
            bind.srogCard.text = it
        }
        bind.nameText.addTextChangedListener {
            bind.nameUser.text = it
            check()
        }

        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableCardObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableCardObserver)
    }

    private val disableCardObserver = Observer<Unit> {
        bind.addNow.isEnabled = false
    }
    private val enableCardObserver = Observer<Unit> {
        bind.addNow.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private var successObserver = Observer<String> {
        val bundle = Bundle()
        bundle.putString("code", bind.codeText.text.toString())
        arguments = bundle
        findNavController().navigate(R.id.action_addCardScreen_to_veryfyCardScreen, bundle)
    }

    private fun check() {
        bind.addNow.isEnabled = boolCodeText && boolDateText && boolNameText
    }
}