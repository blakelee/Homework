package net.blakelee.homework.presenters

import net.blakelee.homework.activities.MainActivity
import net.blakelee.homework.databases.ClassRepository
import net.blakelee.homework.interfaces.MainActivityPresenterInterface
import net.blakelee.homework.models.Classes
import org.jetbrains.anko.ctx

class MainActivityPresenter (private var parent : MainActivity?) : MainActivityPresenterInterface{

    private val repo = ClassRepository(parent!!.ctx)

    override fun loadClasses() : MutableList<Classes> {
        return repo.getClasses()
    }
    override fun onDestroy() {
        parent = null
    }

}