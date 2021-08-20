package com.igdev.exampleapp.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igdev.exampleapp.R
import com.igdev.exampleapp.models.Person

class PeopleAdapter(
    private var people: List<Person>
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>()  {

    //region ViewHolder

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvPersonName: TextView = view.findViewById(R.id.tvPersonName)
        val tvPersonPicture: TextView = view.findViewById(R.id.tvPersonPicture)
    }

    //endregion

    //region Overrides

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]

        holder.tvPersonName.text = person.name
        holder.tvPersonPicture.text = person.picture
    }

    override fun getItemCount(): Int = people.size

    //endregion
}