package net.blakelee.homework.presenters

import io.realm.Realm
import net.blakelee.homework.activities.EditClassActivity
import net.blakelee.homework.databases.ClassDetailsRepository
import net.blakelee.homework.interfaces.EditClassPresenterInterface
import net.blakelee.homework.models.ClassDetails
import kotlin.properties.Delegates

class EditClassPresenter(className : String? = null, private var view : EditClassActivity?) : EditClassPresenterInterface {

    private var realm : Realm by Delegates.notNull()
    private var classDetails : ClassDetails? = null
    private val classDetailsRepository = ClassDetailsRepository()

    init {
        Realm.init(view)
        realm = Realm.getDefaultInstance()

        if (className != null)
            classDetailsRepository.getClass(className, this::onGetClassSuccess)
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
    }

    /**
     * This event should fire when the save button is called and className is not null
     * The parent activity needs to notifyItemChanged on its adapter
     */
    override fun onChangeClassSuccess() {

    }

    /**
     * This event should fire when the save button is called and className is null
     * The parent activity needs to call notifyItemAdded on its adapter
     */
    override fun onAddClassSuccess() {

    }

    override fun onDestroy() {
        view = null
    }
}