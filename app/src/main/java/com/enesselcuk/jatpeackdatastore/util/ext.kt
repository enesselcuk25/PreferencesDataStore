package com.enesselcuk.jatpeackdatastore.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:formatData")
fun TextView.setData(data: String) {
    try {
        val curFormatter = SimpleDateFormat("MMM d, yyyy", Locale.US)
        this.text = curFormatter.toString()
    } catch (ex: ParseException) {
        ex.printStackTrace()
    }

}