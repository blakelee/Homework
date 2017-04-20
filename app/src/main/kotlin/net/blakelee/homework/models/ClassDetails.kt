package net.blakelee.homework.models

data class ClassDetails(val name: String,
                        val days : MutableList<Days>,
                        val location: String,
                        val syllabus: String,
                        val email: String,
                        val phone: String,
                        val hours: Int,
                        val image: String,
                        val professor: String,
                        val finals: Days
)