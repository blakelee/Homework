package net.blakelee.homework.databases

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.save
import com.raizlabs.android.dbflow.kotlinextensions.update
import com.raizlabs.android.dbflow.sql.language.SQLite
import net.blakelee.homework.interfaces.ClassDetailsRepositoryInterface
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.ClassDetailsJSON
import net.blakelee.homework.models.ClassDetailsJSON_Table
import net.blakelee.homework.models.Week
import net.blakelee.homework.presenters.EditClassPresenter

class ClassDetailsRepository(val editClassPresenter: EditClassPresenter) : ClassDetailsRepositoryInterface {

    val json = ClassDetailsJSON()

    //Need to make sure adding a class doesn't conflict with an already existing class
    override fun addClass(classDetails: ClassDetails) {
        json.setClassDetailsFromClass(classDetails)
        json.name = classDetails.name

        val cd = SQLite.select()
                .from(ClassDetailsJSON::class)
                .where(ClassDetailsJSON_Table.name.eq(classDetails.name))
                .querySingle()


        if (cd == null) { //Success
            json.save()
            editClassPresenter.onAddClassSuccess()
        }
        else
            editClassPresenter.onAddClassFailure()
    }

    override fun changeClass(classDetails: ClassDetails) {
        json.setClassDetailsFromClass(classDetails)
        json.name = classDetails.name
        json.update()
        editClassPresenter.onChangeClassSuccess()
    }

    override fun getClass(className: String) {
        val cd = SQLite.select()
                .from(ClassDetailsJSON::class)
                .where(ClassDetailsJSON_Table.name.eq(className))
                .querySingle()

        if (cd == null)
            editClassPresenter.onGetClassFailure(ClassDetails())
        else
            editClassPresenter.onGetClassSuccess(cd.getClassDetailsFromString()!!)
    }

    override fun getWeek(className: String) : MutableList<Week> {
        return mutableListOf(Week())
    }
}