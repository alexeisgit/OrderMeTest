package com.github.tarcv.orderme.app.ui.utils

import java.util.Calendar

class DateUtill {
    companion object {
        fun calculateDateFromToday(daysToAdd: Int): Triple<Int, Int, Int> {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, daysToAdd)
            return Triple(cal.get(Calendar.DATE),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.YEAR))
        }
    }
}