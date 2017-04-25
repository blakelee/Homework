package net.blakelee.homework.models

data class ClassDetails(var name: String = "",
                        var week: MutableList<Week> = mutableListOf(Week()),
                        var location: String = "",
                        var syllabus: String = "",
                        var email: String = "",
                        var phone: String = "",
                        var hours: Int? = null,
                        var image: String = "",
                        var professor: String = "",
                        var finals: Day = Day()
)