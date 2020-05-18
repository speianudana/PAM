package com.example.mychatapplication

import com.example.mychatapplication.Utils.Companion.anyValue
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.screens.events.mvp.EventModel
import com.example.mychatapplication.screens.events.mvp.EventPresenter
import com.example.mychatapplication.screens.events.mvp.EventView
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class EventsScreenPresenterTest {
    private lateinit var eventPresenter: EventPresenter
    private lateinit var eventModel: EventModel
    private lateinit var eventView: EventView
    private lateinit var rxSchedulers: RxSchedulers
    private lateinit var serverRequestService: ServerRequestService
    @Before
    fun setup() {
        eventModel = Mockito.mock(EventModel::class.java)
        eventView = Mockito.mock(EventView::class.java)
        rxSchedulers = Mockito.mock(RxSchedulers::class.java)
        serverRequestService = Mockito.mock(ServerRequestService::class.java)
        eventPresenter = EventPresenter(eventModel, eventView, rxSchedulers, serverRequestService)
        Mockito.`when`(rxSchedulers.background()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(rxSchedulers.network()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(eventView.observeItemClicksSubscription()).thenReturn(Observable.empty())
    }

    @Test
    fun testPresenter_shouldLoadEvent() {
        val event = EventItem("id", "test", "test", "test", "test", "test")
        Mockito.`when`(eventModel.getAuthToken()).thenReturn("test")
        Mockito.`when`(serverRequestService.getEvents(anyValue())).thenReturn(Observable.just(listOf(event)))
        eventPresenter.onCreate()
        Mockito.verify(eventView).loadData(anyValue())
    }
}
