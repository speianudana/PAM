package com.example.mychatapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mychatapplication.R
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.viewHolders.EventItemViewHolder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class EventRecyclerViewAdapter(private val dataSet: Array<EventItem>) : RecyclerView.Adapter<EventItemViewHolder>() {

    private val clickSubject = PublishSubject.create<Int>()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_cell_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters

        return EventItemViewHolder(view, clickSubject)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    private fun getItem(position: Int) = dataSet[position]

    fun observeClicks(): Observable<EventItem> = clickSubject.map { getItem(it) }
}