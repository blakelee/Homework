package net.blakelee.homework.databases

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.SQLite
import net.blakelee.homework.interfaces.ClassDetailsRepositoryInterface
import net.blakelee.homework.models.*


class ClassDetailsRepository : ClassDetailsRepositoryInterface {

    val classesRepository = ClassesRepository()

    //Need to make sure adding a class doesn't conflict with an already existing class
    override fun addClass(classDetails: ClassDetails) : Boolean {

        val cd = SQLite.select()
                .from(ClassDetails::class)
                .where(ClassDetails_Table.name.eq(classDetails.name))
                .querySingle()

        var ret = false
        if (cd == null) { //Success
            ret = classDetails.save()
            with(classDetails) {
                classesRepository.addClass(Classes(id, name, weeks, icon))
            }
        }
        return ret
    }

    override fun changeClass(classDetails: ClassDetails) : Boolean {

        val ret = classDetails.update()
        val classes = classesRepository.getClass(classDetails.id)!!
        classes.name = classDetails.name
        classes.weeks = classDetails.weeks
        classes.icon = classDetails.icon
        classesRepository.changeClass(classes)
        return ret
    }

    override fun getClass(classId : Int?) : ClassDetails {
        var cd : ClassDetails? = null

        if (classId != null) {
            cd = SQLite.select()
                    .from(ClassDetails::class)
                    .where(ClassDetails_Table.id.eq(classId))
                    .querySingle()
        }

        if (cd == null)
            return ClassDetails(0)
        else
            return cd
    }

    override fun deleteClass(classId: Int): Boolean {
        val cd : ClassDetails = getClass(classId)
        val c : Classes? = ClassesRepository().getClass(classId)

        cd.delete()
        c?.delete()
        return true
    }
}