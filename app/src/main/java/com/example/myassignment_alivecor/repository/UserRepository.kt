package com.example.myassignment_alivecor.repository

import java.util.*

class UserRepository {
    var ageInYear = 0
    var ageInMonth: Int = 0
    var ageInDay: Int = 0

    fun getAge(year: Int, monthOfYear: Int, dayOfMonth: Int, callback: (String) -> Unit) {
        var dateString = ""
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.set(year, monthOfYear, dayOfMonth)
        val currentYear: Int = today.get(Calendar.YEAR)
        val currentMonth: Int = today.get(Calendar.MONTH)
        val currentDay: Int = today.get(Calendar.DAY_OF_MONTH)
        ageInYear = currentYear - year
        if (currentMonth >= monthOfYear) {
            ageInMonth = currentMonth - monthOfYear;
        } else {
            ageInMonth = currentMonth - monthOfYear;
            ageInMonth += 12;
            ageInYear--
        }
        if (currentDay >= dayOfMonth) {
            ageInDay = currentDay - dayOfMonth;
        } else {
            ageInDay = currentDay - dayOfMonth;
            ageInDay += 31;
            if (ageInMonth == 0) {
                ageInMonth = 11;
                ageInYear--;
            } else {
                ageInMonth--;
            }
        }
        if (ageInDay < 0 || ageInMonth < 0 || ageInYear < 0) {
            dateString = "Invalidate date of birth"
        } else {
            dateString = "$ageInYear years , $ageInMonth months , $ageInDay days"
        }
        callback(dateString)
    }

}