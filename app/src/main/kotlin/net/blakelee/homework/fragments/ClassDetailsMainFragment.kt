package net.blakelee.homework.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import net.blakelee.homework.R
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.viewmodels.ClassDetailsViewModel
import net.blakelee.homework.views.ClassDetailsMainFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ClassDetailsMainFragment(val classId: Long) : Fragment() {

    lateinit var classDetails : ClassDetails
    lateinit var viewModel : ClassDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ClassDetailsMainFragmentUI(classDetails).createView(AnkoContext.create(ctx, this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ClassDetailsViewModel::class.java)
        classDetails = viewModel.getClass(classId)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.class_details_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}