package com.example.qurecurrencyconverter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qurecurrencyconverter.data.model.CurrencyConversionHistoryDataDBModel
import com.example.qurecurrencyconverter.databinding.LayoutConversionHisBinding

class CurrencyConversionHistoryRVAdapter : RecyclerView.Adapter<CurrencyConversionHistoryRVAdapter.CurrencyConversionHistoryViewHolder>() {

    inner class CurrencyConversionHistoryViewHolder(private val binding: LayoutConversionHisBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun bind(currencyConversionHistoryDataDBModel: CurrencyConversionHistoryDataDBModel) {
                binding.tvCalHisCalcNum.text = String.format("%s to %s",
                    currencyConversionHistoryDataDBModel.firstCurr,
                    currencyConversionHistoryDataDBModel.secondCurr)
            }
        }

    private val dataList = mutableListOf<CurrencyConversionHistoryDataDBModel>()

    fun updateList(list: List<CurrencyConversionHistoryDataDBModel>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyConversionHistoryViewHolder {
        return CurrencyConversionHistoryViewHolder(LayoutConversionHisBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CurrencyConversionHistoryViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}