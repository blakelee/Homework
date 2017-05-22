package net.blakelee.homework.databases;

import android.arch.persistence.room.*;

import net.blakelee.homework.models.ClassDetails;
import net.blakelee.homework.models.Classes;
import net.blakelee.homework.utils.WeeksConverters;

import java.util.List;

/*
@Dao
@TypeConverters(WeeksConverters::class)

interface ClassDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertClass(cd : ClassDetails)

    @Query("SELECT name FROM details WHERE name = :p0 LIMIT 1")
    fun findByName(name : String) : String?

    @Query("SELECT * FROM details WHERE id = :p0 LIMIT 1")
    fun getClassesById(id : Long) : ClassDetails

    @Query("SELECT id, name, weeks, icon FROM details")
    fun getClasses() : MutableList<Classes>

    @Delete
    fun deleteClass(id : Long)

    @Update
    fun updateClass(cd : ClassDetails)
}
*/

@Dao
@TypeConverters({WeeksConverters.class})
public interface ClassDao {

    @Insert
    Long insertClass(ClassDetails cd);

    @Query("SELECT name FROM class_details WHERE name = :name LIMIT 1")
    String findByName(String name);

    @Query("SELECT * FROM class_details WHERE id = :id LIMIT 1")
    ClassDetails getClassesById(Long id);

    @Query("SELECT id, name, weeks, icon FROM class_details")
    List<Classes> getClasses();

    //@Delete
    //void deleteClass(Long id);

    @Update
    void updateClass(ClassDetails cd);
}