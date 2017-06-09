package net.blakelee.homework.fragments

import android.os.Bundle
import android.view.*
import net.blakelee.homework.R
import net.blakelee.homework.base.BaseLifecycleFragment
import net.blakelee.homework.viewmodels.TestsViewModel

class TestsFragment(val classId: Long, val color: Int): BaseLifecycleFragment<TestsViewModel>() {

    override val viewModelClass = TestsViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.class_details_alt, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}