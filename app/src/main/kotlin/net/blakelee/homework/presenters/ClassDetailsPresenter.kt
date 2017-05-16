package net.blakelee.homework.presenters

import net.blakelee.homework.databases.ClassRepository
import net.blakelee.homework.fragments.ClassDetailsMainFragment
import net.blakelee.homework.interfaces.ClassDetailsPresenterInterface
import org.jetbrains.anko.support.v4.ctx

class ClassDetailsPresenter(private val classId : Long, private var parent : ClassDetailsMainFragment?) : ClassDetailsPresenterInterface {

    override fun loadClassDetails(classId: Long) = ClassRepository(parent!!.ctx).getClass(classId)

    override fun onDestroy() {
        parent = null
    }
}