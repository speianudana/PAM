package com.example.mychatapplication

import com.example.mychatapplication.Utils.Companion.anyValue
import com.example.mychatapplication.models.User
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.screens.settings.mvp.SettingsModel
import com.example.mychatapplication.screens.settings.mvp.SettingsPresenter
import com.example.mychatapplication.screens.settings.mvp.SettingsView
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SettingsPresenterTest {
    private lateinit var settingsPresenter: SettingsPresenter
    private lateinit var settingsModel: SettingsModel
    private lateinit var settingsView: SettingsView
    private lateinit var rxSchedulers: RxSchedulers
    private lateinit var serverRequestService: ServerRequestService

    @Before
    fun setup() {
        settingsModel = mock(SettingsModel::class.java)
        settingsView = mock(SettingsView::class.java)
        rxSchedulers = mock(RxSchedulers::class.java)
        serverRequestService = mock(ServerRequestService::class.java)
        `when`(rxSchedulers.background()).thenReturn(Schedulers.trampoline())
        `when`(rxSchedulers.network()).thenReturn(Schedulers.trampoline())
        `when`(settingsView.observeAppSettingsClicks()).thenReturn(Observable.empty())
        settingsPresenter = SettingsPresenter(settingsModel, settingsView, rxSchedulers, serverRequestService)
    }

    @Test
    fun testPresenter_showUserSettings() {
        val user = User("id", "username")
        `when`(settingsModel.getAuthToken()).thenReturn("test")
        `when`(serverRequestService.userInfo(anyValue())).thenReturn(Observable.just(user))
        settingsPresenter.onCreate()
        verify(settingsView).setData(anyValue(), anyValue())
    }
}
