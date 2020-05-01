package com.example.mychatapplication.screens.userScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.userScreen.mvp.UserScreenPresenter
import com.example.mychatapplication.screens.userScreen.mvp.UserScreenView
import javax.inject.Inject

class UserFragment : Fragment() {
    companion object {
        const val TAG = "UserFragment"
    }

    @Inject
    lateinit var presenter: UserScreenPresenter

    @Inject
    lateinit var view: UserScreenView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DaggerUserScreenComponent.builder()
            .userScreenModule(UserScreenModule(requireActivity()))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

}