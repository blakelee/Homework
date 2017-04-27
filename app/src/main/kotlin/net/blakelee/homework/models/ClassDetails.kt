package net.blakelee.homework.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ClassDetails (

        @PrimaryKey
        @Index
        open var name: String = "",

        open var week: RealmList<Week> = RealmList(Week()),
        open var location: String = "",
        open var syllabus: String = "",
        open var email: String = "",
        open var phone: String = "",
        open var hours: Int? = null,
        open var image: String = "",
        open var professor: String = "",
        open var finals: Day = Day()
) : RealmObject()
