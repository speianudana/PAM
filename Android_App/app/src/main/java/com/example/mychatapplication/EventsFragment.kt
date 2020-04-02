package com.example.mychatapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychatapplication.adapters.EventRecyclerViewAdapter
import com.example.mychatapplication.models.EventItem
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EventsFragment : Fragment() {

    companion object {
        const val TAG = "EventsFragment"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: EventRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        val service =
            RetrofitClientInstance.getRetrofitInstance()!!.create(ServerRequestService::class.java)
        val call = service.getEvents(RetrofitClientInstance.getToken(this.context!!)?.token!!)

        call.enqueue(object : Callback<List<EventItem>> {
            override fun onFailure(call: Call<List<EventItem>>, t: Throwable) {
                showError()
                progressBar.visibility = View.GONE
            }

            override fun onResponse(call: Call<List<EventItem>>, response: Response<List<EventItem>>) {
                if (response.code() == 200 && response.body()!=null) {
                    loadData(view, response.body()!!)
                } else {
                    showError()
                }
                progressBar.visibility = View.GONE
            }

        })
    }

    fun showError() {
        Toast.makeText(context, "Server error or check network connection", Toast.LENGTH_LONG).show()
    }

    private fun loadData(view: View, eventItemList: List<EventItem>) {
        viewAdapter = EventRecyclerViewAdapter(eventItemList.toTypedArray())
        recyclerView = view.findViewById<RecyclerView>(R.id.events_recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        compositeDisposable.add(observeItemClicksSubscription())
    }

    private fun observeItemClicksSubscription(): Disposable {
        return viewAdapter.observeClicks()
            .subscribe {
                val intent= Intent(context, ItemInfoActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
    }
}