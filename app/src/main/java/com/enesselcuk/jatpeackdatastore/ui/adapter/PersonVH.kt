package com.enesselcuk.jatpeackdatastore.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.enesselcuk.jatpeackdatastore.R
import com.enesselcuk.jatpeackdatastore.data.Person
import com.enesselcuk.jatpeackdatastore.data.PersonPriority
import com.enesselcuk.jatpeackdatastore.databinding.ItemPersonBinding
import com.enesselcuk.jatpeackdatastore.util.ObjectCommon.dateFormat

class PersonVH(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(person: Person) {


        binding.textViewName.text = person.name
        binding.deadline.text = dateFormat.format(person.date)

        val color = if (person.completed) {
            R.color.white
        } else {
            R.color.black
        }
        itemView.setBackgroundColor(
            ContextCompat.getColor(itemView.context, color)
        )
        setTaskPriority(person)
    }

    private fun setTaskPriority(person: Person) {
        val textColor = when (person.priority) {
            PersonPriority.HIGH -> R.color.red_wine
            PersonPriority.MEDIUM -> R.color.yellow
            PersonPriority.LOW -> R.color.brainstem_grey
        }

        binding.priority.setTextColor(ContextCompat.getColor(itemView.context, textColor))
    }
}