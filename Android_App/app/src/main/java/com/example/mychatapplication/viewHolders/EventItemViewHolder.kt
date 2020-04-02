package com.example.mychatapplication.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychatapplication.R
import com.example.mychatapplication.models.EventItem
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*
import java.text.SimpleDateFormat


class EventItemViewHolder(view: View, clickSubject: PublishSubject<Int>): RecyclerView.ViewHolder(view) {

    private var headerTextView: TextView = view.findViewById(R.id.headerTextView)
    private var dateTextView: TextView = view.findViewById(R.id.dateTextView)
    private var addressTextView: TextView = view.findViewById(R.id.addressTextView)
    private var imageView: ImageView = view.findViewById(R.id.imageView)
    var simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

    init {
        view.setOnClickListener { clickSubject.onNext(adapterPosition) }
    }

    fun bind(item: EventItem) {
        headerTextView.text = item.title
        addressTextView.text = item.address
        val date = Date(item.date.toLong()*1000)
        dateTextView.text = simpleDateFormat.format(date)
        Picasso.get().load(item.image).into(imageView)
    }
}