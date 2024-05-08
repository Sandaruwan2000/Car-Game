package com.example.game.models

import com.example.game.models.validation.ValidationResult

class FormData(

    private var username:String,

) {

    fun validateUsername():ValidationResult{
        return if(username.isEmpty()){
            ValidationResult.Empty("Enter the Username")
        }else{
            ValidationResult.Valid
        }
    }









}