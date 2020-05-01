package com.example.mychatapplication.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors


class RxSchedulers {

  private val NETWORK_EXECUTOR = Executors.newCachedThreadPool()
  private val NETWORK_SCHEDULER = Schedulers.from(NETWORK_EXECUTOR)
  private val BACKGROUND_EXECUTOR = Executors.newCachedThreadPool()
  private val BACKGROUND_SCHEDULER = Schedulers.from(BACKGROUND_EXECUTOR)

  fun androidUI(): Scheduler {
    return AndroidSchedulers.mainThread()
  }

  fun io(): Scheduler {
    return Schedulers.io()
  }

  fun computation(): Scheduler {
    return Schedulers.computation()
  }

  fun network(): Scheduler {
    return NETWORK_SCHEDULER
  }

  fun background(): Scheduler {
    return BACKGROUND_SCHEDULER
  }

}