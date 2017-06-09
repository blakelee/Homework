package net.blakelee.homework.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import net.blakelee.homework.fragments.ClassDetailsMainFragment
import net.blakelee.homework.fragments.HomeworkFragment
import net.blakelee.homework.fragments.NotesFragment
import net.blakelee.homework.fragments.TestsFragment

class ClassDetailsPagerAdapter(fragmentManager: FragmentManager, var numTabs: Int, val classId: Long, val color: Int) : FragmentStatePagerAdapter(fragmentManager){

    override fun getCount(): Int = numTabs

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return ClassDetailsMainFragment(classId)
            1 -> return HomeworkFragment(classId, color)
            2 -> return TestsFragment(classId, color)
            3 -> return NotesFragment(classId, color)
            else -> return null
        }
    }
}