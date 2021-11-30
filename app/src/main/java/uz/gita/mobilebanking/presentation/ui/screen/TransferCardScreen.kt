package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.data.responce.trasfer.ResponseTransferFee
import uz.gita.mobilebanking.databinding.TransferCardScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.TransferCardViewModelImpl

@AndroidEntryPoint
class TransferCardScreen : Fragment(R.layout.transfer_card_screen) {
    private val bind by viewBinding(TransferCardScreenBinding::bind)
    private val viewModel by viewModels<TransferCardViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var code = ""
        var nameCard = ""
        arguments?.let {
            code = it.getString("codeTransfer").toString()
            nameCard = it.getString("nameTransferCard").toString()
        }
        val senderId = 17

//        bind.codeTransferCard.addTextChangedListener {
//            viewModel.transferFee(TransferFeeRequest(bind.tansferMoney.text.toString().toInt(), bind.codeTransferCard.text.toString(), senderId))
//        }
        bind.sendMondey.setOnClickListener {
            viewModel.sendMoney(RequestMoneyTransfer(bind.tansferMoney.text.toString().toInt(), bind.codeTransferCard.text.toString(), senderId))
        }
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enabledLiveDataObserver)
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableCardObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorLiveDataObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLiveDataObserver)
        viewModel.successNameLiveData.observe(viewLifecycleOwner, successNameLiveDataObserver)
        viewModel.successTransferFeeLiveData.observe(viewLifecycleOwner, successTransferFeeLiveDataObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successLiveDataObserver)

    }

    private val enabledLiveDataObserver = Observer<Unit> {
        bind.sendMondey.isEnabled = true
    }
    private val disableCardObserver = Observer<Unit> {
        bind.sendMondey.isEnabled = false
    }
    private val errorLiveDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val progressLiveDataObserver = Observer<Boolean> {
//        if (it) bind.progress.show()
//        else bind.progress.hide()
    }

    private val successNameLiveDataObserver = Observer<String> {
//        bind.inputNumberLayout.helperText = it
    }
    private val successTransferFeeLiveDataObserver = Observer<ResponseTransferFee> {
//        bind.amountLayout.helperText = "Transfer fee :${it.data}"
    }

    private val successLiveDataObserver = Observer<Unit> {
        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }
}