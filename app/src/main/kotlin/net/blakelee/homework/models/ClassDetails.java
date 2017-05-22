package net.blakelee.homework.models;

import android.arch.persistence.room.*;
import android.support.annotation.Nullable;

import net.blakelee.homework.utils.WeeksConverters;

/**
@Entity(tableName = "class_details", indices = arrayOf(Index("name", unique = true)))
data class ClassDetails (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    var location: String = "",
    var syllabus: String = "",
    var email: String = "",
    var phone: String = "",
    var hours: Int? = null,
    var professor: String = "",

    @ColumnInfo(name = "icon")
    var icon: String = "",

    var ringmode: Int? = null
) {
    @ColumnInfo(name = "weeks")
    var weeks: Weeks = Weeks()
}
*/

@Entity(tableName = "class_details", indices = {@Index(value = {"name"}, unique = true)})
public class ClassDetails {

    @PrimaryKey(autoGenerate = true)
    @Nullable
    public Long id = null;
    public String name= "";
    public String location = "";
    public String syllabus = "";
    public String email = "";
    public String phone = "";
    public Integer hours = null;
    public String professor = "";
    public String icon = "";
    public Integer ringmode = null;

    @TypeConverters({WeeksConverters.class})
    public Weeks weeks = new Weeks();
}
