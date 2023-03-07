package com.rollcake.tripPhoto.ui.detail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.rollcake.tripPhoto.R



@BindingAdapter("distText")
fun bindTextViewTomUnit(textView: TextView, number: String) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.m_unit_format), number.toDouble())
}

@BindingAdapter("typeText")
fun bindTextViewToContentTypeUnit(textView: TextView, contentType: String) {
    textView.text = contentType
}