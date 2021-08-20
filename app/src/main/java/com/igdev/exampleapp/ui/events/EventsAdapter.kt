package com.igdev.exampleapp.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.igdev.exampleapp.R
import com.igdev.exampleapp.extensions.toCalendar
import com.igdev.exampleapp.extensions.toPriceFormat
import com.igdev.exampleapp.extensions.toShortMonth
import com.igdev.exampleapp.models.Event
import java.util.*

class EventsAdapter(
    private val events: List<Event>,
    private val onItemClick: ((String) -> Unit)
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>()  {
    //region ViewHolder

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvEventTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvEventDescription)
        val tvPrice: TextView = view.findViewById(R.id.tvEventPrice)
        val tvDay: TextView = view.findViewById(R.id.tvEventDay)
        val tvMonth: TextView = view.findViewById(R.id.tvEventMonth)
        val tvYear: TextView = view.findViewById(R.id.tvEventYear)
        val tvParticipants: TextView = view.findViewById(R.id.tvEventParticipants)
    }

    //endregion

    //region Overrides

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]

        val calendar = event.date.toCalendar()
        val day = calendar.get(Calendar.DAY_OF_MONTH).toString()
        val month = calendar.get(Calendar.MONTH).toShortMonth()
        val year = calendar.get(Calendar.YEAR).toString()

        holder.tvDay.text = day
        holder.tvMonth.text = month
        holder.tvYear.text = year

        holder.tvTitle.text = event.title
        holder.tvDescription.text = event.description
        holder.tvPrice.text = event.price.toPriceFormat()
        holder.tvParticipants.text = event.people.size.toString()

        holder.itemView.setOnClickListener {
            onItemClick.invoke(event.id)
        }
    }

    override fun getItemCount(): Int = events.size

    //endregion
}