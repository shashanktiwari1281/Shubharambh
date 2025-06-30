package com.example.shubharambh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private val context: Context,
private val personList: List<Person>
) : RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder>() {

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val designation: TextView = view.findViewById(R.id.designation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.name.text = person.name
        holder.designation.text=person.designation
    }

    override fun getItemCount(): Int = personList.size
}
