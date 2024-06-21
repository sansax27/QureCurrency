package com.example.qurecurrencyconverter.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.qurecurrencyconverter.Utils
import com.example.qurecurrencyconverter.Utils.getStringSharedPreference
import com.example.qurecurrencyconverter.Utils.saveSharedPreferenceString
import com.example.qurecurrencyconverter.data.UIStatus
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import com.example.qurecurrencyconverter.databinding.FragmentConversionBinding
import com.example.qurecurrencyconverter.viewmodel.ConversionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConversionFragment : Fragment() {


    private val binding: FragmentConversionBinding by lazy {
        FragmentConversionBinding.inflate(layoutInflater)
    }

    private val viewModel: ConversionViewModel by viewModels()
    private val currencySymbolList = mutableListOf<String>()
    private val conversionRateList = mutableListOf<Double>()
    private val conversionDecimals = mutableListOf(1,2,3)
    private var conversionRate = 1.0
    private var firstCurrPosition = 0
    private var secondCurrPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.getCurrencyData().collect{ currencyList ->
                if (currencyList.isEmpty()) {
                    viewModel.fetchCurrencyDataLD.observe(viewLifecycleOwner) {
                        when(it) {
                            is UIStatus.Loading -> {}
                            is UIStatus.Failure -> {}
                            is UIStatus.Success -> {

                            }
                        }
                    }
                } else {
                    val storedUserPreference = requireActivity().getStringSharedPreference("user_pref") ?: ""

                    for (currencyIdx in currencyList.indices) {
                        currencySymbolList.add(currencyList[currencyIdx].currSymbol)
                        conversionRateList.add(currencyList[currencyIdx].currRate)
                        if (storedUserPreference.isNotEmpty()) {
                            if (storedUserPreference.split("||")[0]==currencyList[currencyIdx].currSymbol) {
                                firstCurrPosition = currencyIdx
                            }
                            if (storedUserPreference.split("||")[1]==currencyList[currencyIdx].currSymbol) {
                                secondCurrPosition = currencyIdx
                            }
                        }
                    }
                    binding.spConvCurr1.adapter = ArrayAdapter(requireContext(), com.google.android.
                    material.R.layout.support_simple_spinner_dropdown_item, currencySymbolList)
                    binding.spConvCurr2.adapter = ArrayAdapter(requireContext(), com.google.android.
                    material.R.layout.support_simple_spinner_dropdown_item, currencySymbolList)
                    binding.spConvCurrDec.adapter = ArrayAdapter(requireContext(), com.google.android.
                    material.R.layout.support_simple_spinner_dropdown_item, conversionDecimals)
                    binding.spConvCurrDec.setSelection(1)
                    binding.spConvCurr1.setSelection(firstCurrPosition)
                    binding.spConvCurr2.setSelection(secondCurrPosition)
                    conversionRate = conversionRateList[secondCurrPosition]/conversionRateList[firstCurrPosition]
                }
            }
        }
        binding.etConvCurr1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.length!=0) {
                    binding.tvConvCurr2.text = String.format(
                        "%.${
                            binding.spConvCurrDec.selectedItem
                                .toString().toInt()
                        }f", p0.toString().toDouble() * conversionRate
                    )
                } else {
                    binding.tvConvCurr2.text = ""
                }
            }
        })
        binding.spConvCurr1.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                firstCurrPosition = p2
                conversionRate = conversionRateList[secondCurrPosition]/conversionRateList[firstCurrPosition]
                viewModel.saveConversionHistoryData(
                    CurrencyConversionHistoryDataDBModel(currencySymbolList[firstCurrPosition],
                        currencySymbolList[secondCurrPosition]))
                binding.etConvCurr1.setText("1", TextView.BufferType.EDITABLE)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.spConvCurr2.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                secondCurrPosition = p2
                conversionRate = conversionRateList[secondCurrPosition]/conversionRateList[firstCurrPosition]
                binding.etConvCurr1.setText("1", TextView.BufferType.EDITABLE)
                viewModel.saveConversionHistoryData(
                    CurrencyConversionHistoryDataDBModel(currencySymbolList[firstCurrPosition],
                        currencySymbolList[secondCurrPosition]))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        binding.btCurrDefault.setOnClickListener {
            requireActivity().saveSharedPreferenceString("user_pref",
                currencySymbolList[firstCurrPosition]+"||"+currencySymbolList[secondCurrPosition])
            Toast.makeText(context, "Default Set!!", Toast.LENGTH_SHORT).show()
        }
        binding.btCurrSwap.setOnClickListener {
            firstCurrPosition = secondCurrPosition.also {
                secondCurrPosition = firstCurrPosition
            }
            binding.spConvCurr1.setSelection(firstCurrPosition)
            binding.spConvCurr2.setSelection(secondCurrPosition)

            binding.etConvCurr1.setText("1", TextView.BufferType.EDITABLE)
            viewModel.saveConversionHistoryData(
                CurrencyConversionHistoryDataDBModel(currencySymbolList[firstCurrPosition],
                    currencySymbolList[secondCurrPosition]))
        }
    }

}