package com.example.mychatapplication.screens.settings

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.settings.mvp.SettingsPresenter
import com.example.mychatapplication.screens.settings.mvp.SettingsView
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: SettingsPresenter

    @Inject
    lateinit var view: SettingsView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSettingComponent.builder()
            .settingsModule(SettingsModule(this))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)
        setContentView(view)
        presenter.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
