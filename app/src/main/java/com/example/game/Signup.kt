package com.example.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.game.models.FormData
import com.example.game.models.validation.ValidationResult

class Signup : AppCompatActivity() {


    lateinit var username: EditText

    private var count = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username = findViewById(R.id.username)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


    }

    fun displayAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, which ->
            // Add any action needed after pressing Ok
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun submit(v: View) {
        val myform = FormData(
            username.text.toString()
        )

        val usernameValidation = myform.validateUsername()

        when (usernameValidation) {
            is ValidationResult.Valid -> {
                count++
            }
            is ValidationResult.Invalid -> {
            }
            is ValidationResult.Empty -> {
                username.error = usernameValidation.errorMessage
            }


        }

        if (count == 1) {

            val enteredUsername = username.text.toString()
            saveUsername(enteredUsername)
            displayAlert("Success", "You have successfully Registered")
            val intent = Intent(this, Play::class.java)
            startActivity(intent)
        } else {
            count = 0
        }


    }

    private fun saveUsername(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }
}
