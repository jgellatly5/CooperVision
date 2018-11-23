package com.jordangellatly.coopervision.details

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class DetailPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf("Details", "Edit")
    private val PAGE_COUNT = 2

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> DetailFragment.newInstance("Detail Fragment", 0)
            1 -> EditFragment.newInstance("Edit Fragment", 1)
            else -> null
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
