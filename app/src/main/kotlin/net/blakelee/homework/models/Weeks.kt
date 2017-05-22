package net.blakelee.homework.models

data class Weeks (
    var week : MutableList<Week> = mutableListOf(Week())
) {
    fun getWeeksAsString() : String {
        var output = ""
        for(week in this.week)
            output += week.getDayAsString() + " " + week.getStartTimeAsString() + " - " + week.getEndTimeAsString() + "\n"

        return output.dropLast(1) //Drop new line
    }
}
