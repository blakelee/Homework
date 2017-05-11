package net.blakelee.homework.models


class Weeks (
    var week : MutableList<Week> = mutableListOf(Week())
) {

    fun getWeeksAsString() : String {
        var output = ""
        for(week in this.week)
            output += week.getDay() + " " + week.getStartTime() + " - " + week.getEndTime() + "\n"

        return output.dropLast(1) //Drop new line
    }
}
