package com.example.mychatapplication.screens.homeScreen

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.R
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.homeScreen.mvp.HomePresenter
import com.example.mychatapplication.screens.homeScreen.mvp.HomeView
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var view: HomeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerHomeComponent.builder()
            .homeModule(HomeModule(this))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)

        setContentView(view)
        presenter.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                presenter.showSettings()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

