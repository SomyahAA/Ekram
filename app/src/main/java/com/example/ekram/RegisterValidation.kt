package com.example.ekram

import java.util.regex.Pattern

class RegisterValidation {
    private val REGEX_PASSWORD = "^(?=.*[0-9])" +  // a digit must occur at least once
            "(?=.*[a-z])" +  // a lower case letter must occur at least once
            "(?=.*[A-Z])" +  // an upper case letter must occur at least once
            "(?=.*[!@#\\$%\\^&\\*])" +  // a special character must occur at least once
            "(?=\\S+$)" +  // no whitespace allowed in the entire string
            ".{8,}$" // anything, at least eight places though
    private val REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"

    fun emailIsValid(email: String): Boolean {
        val Pattern = Pattern.compile(REGEX_EMAIL)
        val matcher = Pattern.matcher(email)
        return matcher.matches()
    }
    fun passwordValid(password:String):Boolean{
        val pattern = Pattern.compile(REGEX_PASSWORD)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    /**
     * testing types
     *
     * 1- unit testing: test any class that has kotlin code only
     * 2- instrumentation test
     * 3-UI test
     */
}