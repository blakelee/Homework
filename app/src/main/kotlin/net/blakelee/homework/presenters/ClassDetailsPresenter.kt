package net.blakelee.homework.presenters

import net.blakelee.homework.databases.ClassDetailsRepository
import net.blakelee.homework.fragments.ClassDetailsMainFragment
import net.blakelee.homework.interfaces.ClassDetailsPresenterInterface

class ClassDetailsPresenter(private val classId : Int, private var view : ClassDetailsMainFragment?) : ClassDetailsPresenterInterface {

    override fun loadClassDetails(classId: Int) = ClassDetailsRepository().getClass(classId)

    override fun onDestroy() {
        view = null
    }
}