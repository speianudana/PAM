package com.example.mychatapplication.screens.settings.mvp

import android.view.MenuItem
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class SettingsPresenter(
    private val model: SettingsModel,
    private val view: SettingsView,
    private val rxSchedulers: RxSchedulers,
    private val serverRequestService: ServerRequestService
) {

    private val subscriptions = CompositeDisposable()
    private val loadUserInfoSubject = PublishSubject.create<Any>()

    fun onCreate() {
        subscriptions.add(settingsSubscription())
        subscriptions.add(getUserInfo())
        loadUserInfoSubject.onNext(Any())
    }

    private fun settingsSubscription(): Disposable = view.observeAppSettingsClicks()
        .observeOn(rxSchedulers.background())
        .subscribe ({
            model.openSettings()
        }, {
            view.showError()
        })

    private fun getUserInfo(): Disposable = loadUserInfoSubject
        .observeOn(rxSchedulers.background())
        .map { model.getAuthToken() }
        .flatMap {
            serverRequestService.userInfo(it)
        }
        .observeOn(rxSchedulers.network())
        .subscribe ({
            view.setData(it.id, it.username)
        }, {
            view.showError()
        })

    fun onOptionsItemSelected(item: MenuItem) {
        if (item.itemId == android.R.id.home) {
            model.finish()
        }
    }

    fun onDestroy() {
        subscriptions.dispose()
    }
}