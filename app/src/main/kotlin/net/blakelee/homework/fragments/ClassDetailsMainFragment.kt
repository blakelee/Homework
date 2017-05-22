package net.blakelee.homework.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.viewmodels.ClassDetailsViewModel
import net.blakelee.homework.views.ClassDetailsMainFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ClassDetailsMainFragment(val classId: Long) : Fragment() {

    lateinit var classDetails : ClassDetails
    lateinit var viewModel : ClassDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProviders.of(this).get(ClassDetailsViewModel::class.java)
        classDetails = viewModel.getClass(classId)
        return ClassDetailsMainFragmentUI(classDetails).createView(AnkoContext.create(ctx, this))
    }
}