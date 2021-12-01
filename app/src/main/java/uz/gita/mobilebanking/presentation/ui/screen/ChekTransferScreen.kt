package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.requests.trnsfer_money.RequestMoneyTransfer
import uz.gita.mobilebanking.databinding.ChekTransferScreenBinding
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.TransferCardViewModelImpl

@AndroidEntryPoint
class ChekTransferScreen : Fragment(R.layout.chek_transfer_screen) {
    private val bind by viewBinding(ChekTransferScreenBinding::bind)
    private val viewModel by viewModels<TransferCardViewModelImpl>()
    private val value: RequestMoneyTransfer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            var value = it?.getSerializable("valueTransfer")
        }
        bind.back.setOnClickListener {
            findNavController().popBackStack()
        }
        bind.sendMondey.setOnClickListener {
            value?.let {
                viewModel.sendMoney(RequestMoneyTransfer(value.amount, value.receiverPan, value.sender))
            }
        }
    }
}