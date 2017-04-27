package net.blakelee.homework.models

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey

//TODO: Change to null since this is just dummy data
open class Classes (

        @PrimaryKey
        @Index
        var name: String = "Psychology 101",

        var times: String = "MWF 8:00am-10:00am,,,,TT 10:00am-11:00am,,,,S 12:00pm-1:00pm",
        var icon: String = ""
) : RealmObject() {

        fun getTimes() : List<String> = times.split(",,,,")
        fun setTimes(weeks : List<String>?) {
                if (weeks == null)
                        times = ""
                else
                        times = weeks.joinToString(",,,,")
        }
}