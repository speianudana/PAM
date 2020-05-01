package com.example.mychatapplication.screens.events.mvp

import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class EventPresenter(
    private val model: EventModel,
    private val view: EventView,
    private val rxSchedulers: RxSchedulers,
    private val serverRequestService: ServerRequestService
) {

    private val subscriptions = CompositeDisposable()
    private val loadEventsInfoSubject = PublishSubject.create<Any>()

    fun onCreate() {
        subscriptions.add(getUserInfo())
        subscriptions.add(loginSubscription())
        loadEventsInfoSubject.onNext(Any())
    }

    private fun getUserInfo(): Disposable = loadEventsInfoSubject
        .observeOn(rxSchedulers.background())
        .map { model.getAuthToken() }
        .flatMap {
            serverRequestService.getEvents(it)
        }
        .doOnNext { view.showProgressBar(true) }
        .observeOn(rxSchedulers.network())
        .subscribe ({
            view.showProgressBar(false)
            view.loadData(it)
        }, {
            view.showError()
            view.showProgressBar(false)
        })

    private fun loginSubscription(): Disposable = view.observeItemClicksSubscription()
        .observeOn(rxSchedulers.background())
        .subscribe ({
            model.showItemInfo(it.id)
        }, {
            view.showError()
        })
}