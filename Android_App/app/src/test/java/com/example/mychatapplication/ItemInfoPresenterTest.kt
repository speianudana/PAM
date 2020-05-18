package com.example.mychatapplication

import com.example.mychatapplication.Utils.Companion.anyValue
import com.example.mychatapplication.models.EventItem
import com.example.mychatapplication.network.ServerRequestService
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoModel
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoPresenter
import com.example.mychatapplication.screens.itemInfo.mvp.ItemInfoView
import com.example.mychatapplication.util.RxSchedulers
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ItemInfoPresenterTest {
    private lateinit var itemInfoPresenter: ItemInfoPresenter
    private lateinit var itemInfoModel: ItemInfoModel
    private lateinit var itemInfoView: ItemInfoView
    private lateinit var rxSchedulers: RxSchedulers
    private lateinit var serverRequestService: ServerRequestService

    @Before
    fun setup() {
        itemInfoModel = Mockito.mock(ItemInfoModel::class.java)
        itemInfoView = Mockito.mock(ItemInfoView::class.java)
        rxSchedulers = Mockito.mock(RxSchedulers::class.java)
        serverRequestService = Mockito.mock(ServerRequestService::class.java)
        Mockito.`when`(rxSchedulers.background()).thenReturn(Schedulers.trampoline())
        Mockito.`when`(rxSchedulers.network()).thenReturn(Schedulers.trampoline())
        itemInfoPresenter = ItemInfoPresenter(itemInfoModel, itemInfoView, rxSchedulers, serverRequestService)
    }

    @Test
    fun testPresenter_showUserSettings() {
        val event = EventItem("id", "test", "test", "test", "test", "test")
        Mockito.`when`(itemInfoModel.getAuthToken()).thenReturn("test")
        Mockito.`when`(serverRequestService.getEvent(anyValue(), anyValue())).thenReturn(Observable.just(event))
        itemInfoPresenter.onCreate()
        itemInfoPresenter.getItemInfo("1")
        Mockito.verify(itemInfoView).setData(anyValue())
    }
}
