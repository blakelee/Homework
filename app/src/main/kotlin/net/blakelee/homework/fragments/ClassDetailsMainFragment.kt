package net.blakelee.homework.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.views.ClassDetailsMainFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ClassDetailsMainFragment(val classDetails: ClassDetails) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ClassDetailsMainFragmentUI(classDetails).createView(AnkoContext.create(ctx, this))
    }
}