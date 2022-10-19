package com.moataz.better_human.better_life.core.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter(value = ["app:isFieldValid", "app:errorMessage"])
fun setErrorText(textInputLayout: TextInputLayout, isValid: Boolean, errorMessage: String) {
    textInputLayout.error = if (isValid) null else errorMessage
}