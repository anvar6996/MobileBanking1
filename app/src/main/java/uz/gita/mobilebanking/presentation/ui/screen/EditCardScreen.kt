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
import uz.gita.mobilebanking.data.requests.card.DeleteCardRequest
import uz.gita.mobilebanking.data.requests.card.EditCardRequest
import uz.gita.mobilebanking.databinding.EditCardScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.EditCardViewModelImpl

@AndroidEntryPoint
class EditCardScreen : Fragment(R.layout.edit_card_screen) {
    private val bind by viewBinding(EditCardScreenBinding::bind)
    private val chekEdit = false
    private val viewModel by viewModels<EditCardViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var code = ""
        var nameCard = ""

        arguments?.let {
            code = it.getString("codeforEdit").toString()
            nameCard = it.getString("nameCardForEdit").toString()
        }
        bind.numberCard.text = code
        bind.nameUserCard.text = nameCard

        bind.transfer.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("codeTransfer", code)
            bundle.putString("nameTransferCard", nameCard)
            arguments = bundle
            findNavController().navigate(R.id.action_editCardScreen_to_transferCardScreen)
        }
        bind.deleteCard.setOnClickListener {
            viewModel.deleteCard(DeleteCardRequest(arguments?.getString("codeforEdit").toString()))
        }
        bind.editSave.setOnClickListener {
            viewModel.editCard(EditCardRequest(arguments?.getString("codeforEdit").toString(), bind.newNameCard.text.toString()))
        }
        bind.editSave.addTextChangedListener {
            chekEdit == it.toString().length >= 4
        }
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableCardObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableCardObserver)
    }

    private val disableCardObserver = Observer<Unit> {
        bind.deleteCard.isEnabled = false
        bind.editSave.isEnabled = false
    }
    private val enableCardObserver = Observer<Unit> {
        bind.editSave.isEnabled = true
        bind.deleteCard.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private var successObserver = Observer<String> {
        findNavController().popBackStack()
    }

}
