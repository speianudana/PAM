package com.example.mychatapplication.screens.itemInfo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mychatapplication.application.EventApp
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoPresenter
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoView
import javax.inject.Inject

class ItemInfoActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: ItemInfoPresenter

    @Inject
    lateinit var view: ItemInfoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerItemInfoComponent.builder()
            .itemInfoModule(ItemInfoModule(this))
            .appComponent(EventApp.get(this).getComponent())
            .build().inject(this)

        setContentView(view)
        val id = intent.getStringExtra("id")
        presenter.onCreate()
        presenter.getItemInfo(id)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }
}
