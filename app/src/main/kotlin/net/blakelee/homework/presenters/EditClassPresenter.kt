package net.blakelee.homework.presenters

import com.raizlabs.android.dbflow.config.FlowManager
import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.databases.ClassDetailsRepository
import net.blakelee.homework.interfaces.EditClassPresenterInterface
import net.blakelee.homework.models.ClassDetails
import org.jetbrains.anko.ctx
import com.raizlabs.android.dbflow.config.FlowConfig
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn


class EditClassPresenter(val className : String? = null, private var view : EditClassActivity?) : EditClassPresenterInterface, AnkoLogger {

    private var classDetails : ClassDetails? = null
    private val classDetailsRepository = ClassDetailsRepository(this)

    init {
        FlowManager.init(FlowConfig.Builder(view!!.ctx).build())

        if (className != null)
            classDetailsRepository.getClass(className)
        else
            onGetClassSuccess(ClassDetails())
    }


    /**
     * This event should notify EditClassActivity that it can start construction of
     * the adapter. If it fails then we'll have a blank activity
     */
    override fun onGetClassSuccess(classDetails : ClassDetails) {
        this.classDetails = classDetails
        view?.setClassDetails(classDetails)
        info("Successfully got class $classDetails.name")
    }

    /**
     * This should only happen if the database is corrupt from not deleting previous data
     * or I have dummy data somewhere
     */
    override fun onGetClassFailure(classDetails : ClassDetails) {
        this.classDetails = classDetails
        view?.setClassDetails(classDetails)
        error("Couldn't get class $className")
    }

    fun save() {
        if (className == null)
            classDetailsRepository.addClass(classDetails!!)
        else
            classDetailsRepository.changeClass(classDetails!!)
    }

    /**
     * This event should fire when the save button is called and className is not null
     * The parent activity needs to notifyItemChanged on its adapter
     */
    override fun onChangeClassSuccess() {
        if (className != null) {
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
        view?.validate(false)
        warn("Adding class failed, name conflict")
    }

    override fun onDestroy() {
        view = null
        FlowManager.destroy()
    }
}