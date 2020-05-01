package com.example.mychatapplication.screens.events.mvp

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychatapplication.R
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.screens.events.adapters.EventRecyclerViewAdapter
import io.reactivex.Observable


class EventView(var activity: FragmentActivity) : FrameLayout(activity) {

  private val view: View
  private var recyclerView: RecyclerView
  private var viewAdapter: EventRecyclerViewAdapter
  private var viewManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
  private var progressBar: ProgressBar

  init {
    view = inflate(activity, R.layout.fragment_events, this)
    viewAdapter = EventRecyclerViewAdapter(arrayListOf<EventItem>().toTypedArray())
    progressBar = view.findViewById(R.id.progressBar)
    recyclerView = view.findViewById<RecyclerView>(R.id.events_recycler_view).apply {
      layoutManager = viewManager
      adapter = viewAdapter
    }
  }

  fun loadData(eventItemList: List<EventItem>) {
    activity.runOnUiThread {
      viewAdapter.setData(eventItemList.toTypedArray())
      viewAdapter.notifyDataSetChanged()
    }
  }

  fun observeItemClicksSubscription(): Observable<EventItem> {
    return viewAdapter.observeClicks()
  }

  fun showError() {
    activity.runOnUiThread {
      Toast.makeText(activity, R.string.network_connection_label, Toast.LENGTH_LONG).show()
    }
  }

  fun showProgressBar(showProgress: Boolean) {
    activity.runOnUiThread {
      if (showProgress) {
        progressBar.visibility = View.VISIBLE
      }
      else {
        progressBar.visibility = View.INVISIBLE
      }
    }
  }
}
