package com.jordangellatly.coopervision.details

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class DetailPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("Details", "Edit")

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> DetailFragment.newInstance("Detail Fragment", 0)
            1 -> EditFragment.newInstance("Edit Fragment", 1)
            else -> null
        }
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    companion object {
        private const val PAGE_COUNT = 2
    }
}
