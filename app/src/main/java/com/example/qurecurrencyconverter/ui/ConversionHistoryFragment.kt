package com.example.qurecurrencyconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qurecurrencyconverter.R
import com.example.qurecurrencyconverter.adapter.CurrencyConversionHistoryRVAdapter
import com.example.qurecurrencyconverter.databinding.FragmentConversionHistoryBinding
import com.example.qurecurrencyconverter.viewmodel.ConversionHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConversionHistoryFragment : Fragment() {
    private val binding: FragmentConversionHistoryBinding by lazy {
        FragmentConversionHistoryBinding.inflate(layoutInflater)
    }

    private val viewModel: ConversionHistoryViewModel by viewModels()

    private val adapter = CurrencyConversionHistoryRVAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCurrConvItem.adapter = adapter
        binding.rvCurrConvItem.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        viewModel.viewModelScope.launch {
            viewModel.getCurrencyConversionHistoryData().collect{
                adapter.updateList(it)
            }
        }
    }


}