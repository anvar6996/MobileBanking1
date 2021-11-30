package uz.gita.mobilebanking.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking.R
import uz.gita.mobilebanking.data.MySharedPreferences
import uz.gita.mobilebanking.data.responce.CardData
import uz.gita.mobilebanking.databinding.MainScreenBinding
import uz.gita.mobilebanking.presentation.adapter.CardAdapter
import uz.gita.mobilebanking.presentation.ui.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.main_screen) {
    private val bind by viewBinding(MainScreenBinding::bind)
    private val viewModel by viewModels<MainViewModelImpl>()
    private val listData = ArrayList<CardData>()
    private val adapter by lazy { CardAdapter(listData) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = MySharedPreferences.getPref()
        pref.controllRegisteget = true

        bind.addCard.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_addCardScreen)
        }
        viewModel.getAllCard()
        bind.cardReckler.adapter = adapter
        bind.cardReckler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter.setListenerCard {
            val bundle = Bundle()
            bundle.putString("codeforEdit", it.pan)
            bundle.putString("nameCardForEdit", it.cardName)
            arguments = bundle
            findNavController().navigate(R.id.action_mainScreen_to_editCardScreen, bundle)
        }

        viewModel.errorLivaData.observe(viewLifecycleOwner, errorLivaDataObserver)
//        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLivedataObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successLivaDataObserver)
//        viewModel.successDeleteLiveData.observe(viewLifecycleOwner, successDeleteLiveDataObserver)
//        viewModel.failureDeleteLiveData.observe(viewLifecycleOwner, failureDeleteLiveDataObserver)
//        viewModel.openSendMoneyLiveData.observe(viewLifecycleOwner, openSendMoneyLiveDataObserver)
        bind.acountImage.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreen_to_profileScreen)
        }
    }

    private val errorLivaDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

//    private val progressLivedataObserver = Observer<Boolean>{
//        if (it)vb.progress.show()
//        else vb.progress.hide()
//    }

    private val successLivaDataObserver = Observer<List<CardData>> {
        listData.clear()
        listData.addAll(it)
        adapter.notifyDataSetChanged()
    }

    private val successDeleteLiveDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val failureDeleteLiveDataObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
//    private val openSendMoneyLiveDataObserver = Observer<Boolean>{
//        findNavController().navigate(R.id.action_myCardsScreen_to_transferMoneyScreen)
//    }
}