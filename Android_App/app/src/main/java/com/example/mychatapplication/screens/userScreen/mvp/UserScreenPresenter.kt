package com.example.mychatapplication.screens.userScreen.mvp

import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class UserScreenPresenter(
    private val model: UserScreenModel,
    private val view: UserScreenView,
    private val rxSchedulers: RxSchedulers,
    private val serverRequestService: ServerRequestService
) {

    private val subscriptions = CompositeDisposable()
    private val loadUserInfoSubject = PublishSubject.create<Any>()

    fun onCreate() {
        subscriptions.add(logoutSubscription())
        subscriptions.add(getUserInfo())
        loadUserInfoSubject.onNext(Any())
    }

    private fun getUserInfo(): Disposable = loadUserInfoSubject
        .observeOn(rxSchedulers.background())
        .map { model.getAuthToken() }
        .doOnNext { view.showProgressBar(true) }
        .flatMap {
            serverRequestService.userInfo(it)
        }
        .observeOn(rxSchedulers.network())
        .subscribe ({
            view.showProgressBar(false)
            view.setUsername(it.username)
        }, {
            view.showProgressBar(false)
            view.showError()
        })

    private fun logoutSubscription(): Disposable = view.observeLogoutButtonClicks()
        .observeOn(rxSchedulers.background())
        .map { model.getAuthToken() }
        .doOnNext { view.showProgressBar(true) }
        .flatMap {
            serverRequestService.logout(it)
        }
        .observeOn(rxSchedulers.network())
        .subscribe ({
            view.showProgressBar(false)
            model.moveToLoginScreen()
        }, {
            view.showProgressBar(false)
            view.showError()
        })
}