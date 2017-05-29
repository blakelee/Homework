package net.blakelee.homework.databases

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import net.blakelee.homework.models.ClassDetails
import net.blakelee.homework.models.Classes
import net.blakelee.homework.utils.WeeksConverters

@Dao
@TypeConverters(WeeksConverters::class)
interface ClassDao {

    @Insert
    fun insertClass(cd : ClassDetails) : Long

    @Query("SELECT name FROM class_details WHERE name = :p0 LIMIT 1")
    fun findByName(name : String) : String?

    @Query("SELECT name FROM class_details WHERE name = :p0 AND id != :p1 LIMIT 1")
    fun findByNameGivenId(name: String, id: Long?): String?

    @Query("SELECT * FROM class_details WHERE id = :p0 LIMIT 1")
    fun getClassById(id : Long) : ClassDetails

    @Query("SELECT id, name, weeks, icon FROM class_details")
    fun getClasses() : LiveData<List<Classes>>

    @Query("DELETE FROM class_details WHERE id = :p0")
    fun deleteClass(id : Long)

    @Update
    fun updateClass(cd : ClassDetails)
}