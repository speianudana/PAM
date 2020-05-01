package com.example.mychatapplication.screens.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.events.mvp.EventPresenter
import com.example.mychatapplication.screens.events.mvp.EventView
import javax.inject.Inject


class EventsFragment : Fragment() {

    @Inject
    lateinit var presenter: EventPresenter

    @Inject
    lateinit var view: EventView

    companion object {
        const val TAG = "EventsFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DaggerEventComponent.builder()
            .eventModule(EventModule(requireActivity()))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }
}