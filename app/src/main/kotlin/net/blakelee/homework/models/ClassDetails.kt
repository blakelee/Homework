package net.blakelee.homework.models

data class ClassDetails(val name: String, val icon: String, val times: String,
                        val location: String = "", val syllabus: String = "",
                        val image: String = "", val professor: String = ""
                        ) {

}