package net.blakelee.homework.presenters

import net.blakelee.homework.activities.MainActivity
import net.blakelee.homework.databases.ClassesRepository
import net.blakelee.homework.interfaces.MainActivityPresenterInterface
import net.blakelee.homework.models.Classes

class MainActivityPresenter (private var view : MainActivity?) : MainActivityPresenterInterface{

    private val classesRepository = ClassesRepository()

    override fun loadClasses() : MutableList<Classes> {
        return classesRepository.getClasses()
    }
    override fun onDestroy() {
        view = null
    }

}