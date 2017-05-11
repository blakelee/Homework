package net.blakelee.homework.presenters

import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.databases.ClassDetailsRepository
import net.blakelee.homework.interfaces.EditClassPresenterInterface
import net.blakelee.homework.models.ClassDetails
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn


class EditClassPresenter(val classId : Int?, private var parent: EditClassActivity?) : EditClassPresenterInterface, AnkoLogger {

    private var classDetails : ClassDetails? = null
    private val classDetailsRepository = ClassDetailsRepository()

    init {
            //Gets class on startup
            classDetails = classDetailsRepository.getClass(classId)
            parent?.setClassDetails(classDetails!!)
    }

    fun save() {
        if (classId == null)
            classDetailsRepository.addClass(classDetails!!)
        else
            classDetailsRepository.changeClass(classDetails!!)
    }

    /**
     * This event should fire when the save button is called and className is not null
     * The parent activity needs to notifyItemChanged on its adapter
     */
    override fun onChangeClassSuccess() {
        if (classId != null) {
            info("Class successfully changed")
        }
        else {
            warn("No class found, class being added")
            onAddClassSuccess()
        }
    }

    /**
     * This event should fire when the save button is called and className is null
     * The parent activity needs to call notifyItemAdded on its adapter
     */
    override fun onAddClassSuccess() {
        info("Class successfully added")
    }

    /**
     * This occurs when trying to add a class but the name of the class already exists
     * in the database
     */
    override fun onAddClassFailure() {
        parent?.validate(false)
        warn("Adding class failed, name conflict")
    }

    override fun onDestroy() {
        parent = null
    }
}