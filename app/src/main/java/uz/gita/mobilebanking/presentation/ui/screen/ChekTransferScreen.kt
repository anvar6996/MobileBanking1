package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.databinding.ChekTransferScreenBinding

@AndroidEntryPoint
class ChekTransferScreen : Fragment(R.layout.chek_transfer_screen) {
    private val bind by viewBinding(ChekTransferScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}