package net.blakelee.homework.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.presenters.ClassDetailsPresenter
import net.blakelee.homework.views.ClassDetailsMainFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ClassDetailsMainFragment(val classId: Long) : Fragment() {

    lateinit var classDetails : ClassDetails
    lateinit var presenter : ClassDetailsPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        presenter = ClassDetailsPresenter(classId, this)
        classDetails = presenter.loadClassDetails(classId)!! //Can't ever be null unless you're editing the db manually
        return ClassDetailsMainFragmentUI(classDetails).createView(AnkoContext.create(ctx, this))
    }
}