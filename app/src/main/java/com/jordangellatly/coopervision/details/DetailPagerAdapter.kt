package com.jordangellatly.coopervision.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = PAGE_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DetailFragment.newInstance("Detail Fragment", 0)
            else -> EditFragment.newInstance("Edit Fragment", 1)
        }
    }

    companion object {
        private const val PAGE_COUNT = 2
    }
}
