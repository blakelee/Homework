package net.blakelee.homework.models

//TODO: Change to null since this is just dummy data
data class Classes (
        val name: String = "Psychology 101",
        val times: List<String> = listOf("MWF 8:00am-10:00am", "TT 10:00am-11:00am", "S 12:00pm-1:00pm"),
        val icon: String = ""
)