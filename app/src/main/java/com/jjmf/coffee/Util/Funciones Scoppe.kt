package com.jjmf.coffee.Util

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

infix fun View.click(event:(View)->Unit){
    this.setOnClickListener {
        event(it)
    }
}

fun EditText.clear(til: TextInputLayout){
    this.addTextChangedListener {
        til.error = null
    }
}

fun TextInputLayout.er(){
    this.error = "Campo vacio"
}

fun EditText.texs():String{
    return this.text.toString()
}