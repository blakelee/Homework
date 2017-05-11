package net.blakelee.homework.databases

import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.SQLite
import net.blakelee.homework.interfaces.ClassesRepositoryInterface
import net.blakelee.homework.models.Classes


class ClassesRepository : ClassesRepositoryInterface {

    //ClassDetailsRepository takes care of error checking
    override fun addClass(classes: Classes) = classes.save()
    override fun changeClass(classes: Classes) = classes.update()
    override fun getClasses() = SQLite.select().from(Classes::class).queryList()
    override fun getClass(classId: Int) : Classes? = SQLite.select().from(Classes::class).querySingle()
}