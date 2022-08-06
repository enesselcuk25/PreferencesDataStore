package com.enesselcuk.jatpeackdatastore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.enesselcuk.jatpeackdatastore.R
import com.enesselcuk.jatpeackdatastore.data.Person

class PersonAdapter : ListAdapter<Person, PersonVH>(PersonDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
        return PersonVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_person, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        val itemPosition = getItem(position)

        holder.bind(itemPosition)

    }

}