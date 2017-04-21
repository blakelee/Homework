package net.blakelee.homework.models

data class ClassDetails(val name: String = "",
                        val week: MutableList<Week> = mutableListOf(Week()),
                        val location: String = "",
                        val syllabus: String = "",
                        val email: String = "",
                        val phone: String = "",
                        val hours: Int? = null,
                        val image: String = "",
                        val professor: String = "",
                        val finals: Day = Day()
)