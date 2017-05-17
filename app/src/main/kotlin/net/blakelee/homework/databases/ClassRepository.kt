package net.blakelee.homework.databases

import android.content.Context
import com.raizlabs.android.dbflow.kotlinextensions.and
import com.raizlabs.android.dbflow.kotlinextensions.eq
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.sql.language.SQLite
import net.blakelee.homework.interfaces.ClassDetailsRepositoryInterface
import net.blakelee.homework.models.*
import java.io.File

class ClassRepository(val context: Context) : ClassDetailsRepositoryInterface {

    override fun insertClass(classDetails: ClassDetails) : Boolean {

        val cd = querySingle(classDetails.name, -1) //Make sure there aren't conflicting names

        if (cd == null) { //Success
            val classes = Classes(0, classDetails.name, classDetails.weeks, classDetails.icon)
            val id = classes.insert()
            val temp : File = File(context.filesDir, "temp")

            //Add image if one exists
            if (temp.exists()) {
                val file = File(context.filesDir, id.toString())
                temp.renameTo(file)
            }

            classDetails.id = id
            classDetails.insert()

            return true
        }
        return false
    }

    override fun changeClass(classDetails: ClassDetails) : Boolean {
        val id = classDetails.id
        val cd = querySingle(classDetails.name, id) //Make sure there aren't conflicting names

        if (cd == null) {
            classDetails.update()
            val classes = getClasses(id)
            classes?.name = classDetails.name
            classes?.weeks = classDetails.weeks
            classes?.icon = classDetails.icon
            classes?.update()

            val temp : File = File(context.filesDir, "temp")

            //Change image if one exists
            if (temp.exists()) {
                val file = File(context.filesDir, id.toString())
                file.delete()
                temp.renameTo(file)
            }

            return true
        }
        return false
    }

    override fun getClass(classId : Long?) =
                    SQLite.select()
                    .from(ClassDetails::class)
                    .where(ClassDetails_Table.id.eq(classId))
                    .querySingle()

    fun getClasses(classId : Long?) =
                    SQLite.select()
                    .from(Classes::class)
                    .where(Classes_Table.id.eq(classId))
                    .querySingle()

    override fun getClasses() =
                    SQLite.select()
                    .from(Classes::class)
                    .queryList()

    override fun deleteClass(classId: Long) {
        SQLite.delete(Classes::class.java)
                .where(Classes_Table.id eq classId)
                .async()
                .execute()

        SQLite.delete(ClassDetails::class.java)
                .where(ClassDetails_Table.id eq classId)
                .async()
                .execute()

        //Delete image if one exists
        val file = File(context.filesDir, classId.toString())
        file.delete()
    }

    fun querySingle(name : String, id : Long) = SQLite.select()
            .from(Classes::class)
            .where(Classes_Table.name eq name and Classes_Table.id.notEq(id))
            .querySingle()
}