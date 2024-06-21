package com.example.qurecurrencyconverter.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.qurecurrencyconverter.ui.ConversionFragment
import com.example.qurecurrencyconverter.ui.ConversionHistoryFragment
import com.example.qurecurrencyconverter.ui.MainActivity

class MainActivityVPAdapter(activity: MainActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position==0) {
            ConversionFragment()
        } else {
            ConversionHistoryFragment()
        }
    }


}