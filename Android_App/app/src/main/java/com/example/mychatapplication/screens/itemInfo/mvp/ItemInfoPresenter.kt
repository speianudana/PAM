package com.example.mychatapplication.screens.itemInfo.mvp

import android.view.MenuItem
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class ItemInfoPresenter(
    private val model: ItemInfoModel,
    private val view: ItemInfoView,
    private val rxSchedulers: RxSchedulers,
    private val serverRequestService: ServerRequestService
) {

    private val subscriptions = CompositeDisposable()
    private val loadItemInfoSubject = PublishSubject.create<String>()

    fun onCreate() {
        subscriptions.add(getItemInfoSubscription())
    }

    fun getItemInfo(itemId: String?) {
        itemId.let {
            loadItemInfoSubject.onNext(itemId)
        }
    }

    private fun getItemInfoSubscription(): Disposable = loadItemInfoSubject
        .observeOn(rxSchedulers.background())
        .map { Pair<String, String>(model.getAuthToken(), it) }
        .doOnNext { view.showProgressBar(true) }
        .flatMap {
            serverRequestService.getEvent(it.first, it.second)
        }
        .observeOn(rxSchedulers.network())
        .subscribe ({
            view.showProgressBar(false)
            view.setData(it)
        }, {
            view.showProgressBar(false)
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