package net.blakelee.homework.models

import java.util.*

open class BaseAssignment {
    var id: Long = 0
    var title: String = ""
    var description: String = ""
    var due: Date? = null
    var reminders: MutableList<Reminder>? = null
}