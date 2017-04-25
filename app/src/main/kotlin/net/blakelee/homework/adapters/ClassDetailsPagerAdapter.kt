package net.blakelee.homework.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import net.blakelee.homework.fragments.ClassDetailsMainFragment
import net.blakelee.homework.models.ClassDetails

class ClassDetailsPagerAdapter(fragmentManager: FragmentManager, var numTabs: Int, val classDetails: ClassDetails) : FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int = numTabs

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return ClassDetailsMainFragment(classDetails)
            else -> return null
        }
    }
}