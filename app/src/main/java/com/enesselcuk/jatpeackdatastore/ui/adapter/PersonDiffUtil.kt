package com.enesselcuk.jatpeackdatastore.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.enesselcuk.jatpeackdatastore.data.Person

object PersonDiffUtil : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem
}