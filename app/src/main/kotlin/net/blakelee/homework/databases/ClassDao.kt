package net.blakelee.homework.databases

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Classes
import net.blakelee.homework.utils.WeekConverter

@Dao
@TypeConverters(WeekConverter::class)
interface ClassDao {

    @Insert
    fun insertClass(cd : ClassDetails) : Long

    @Query("SELECT name FROM class_details WHERE name = :name LIMIT 1")
    fun findByName(name : String) : String?

    @Query("SELECT name FROM class_details WHERE name = :name AND id != :id LIMIT 1")
    fun findByNameGivenId(name: String, id: Long?): String?

    @Query("SELECT * FROM class_details WHERE id = :id LIMIT 1")
    fun getClassById(id : Long) : ClassDetails

    @Query("SELECT id, name, weeks, icon, icon_color FROM class_details")
    fun getClasses() : LiveData<List<Classes>>

    @Query("DELETE FROM class_details WHERE id = :id")
    fun deleteClass(id : Long)

    @Update
    fun updateClass(cd : ClassDetails)
}