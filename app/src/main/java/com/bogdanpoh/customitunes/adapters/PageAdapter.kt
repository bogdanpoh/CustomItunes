package com.bogdanpoh.customitunes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()
    private val titleList: ArrayList<String> = arrayListOf()

    fun addFragment(framgment: Fragment, title: String) {
        fragmentList.add(framgment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}