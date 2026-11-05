package org.ianus.revmeet.helper

import android.util.Patterns

object ValidateEmail {

     fun emailCheck(input: String): Boolean {
        // Getting the user input

         return Patterns.EMAIL_ADDRESS.matcher(input).matches()
     }
}