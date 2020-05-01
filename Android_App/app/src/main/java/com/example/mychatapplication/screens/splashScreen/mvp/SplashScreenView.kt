package com.example.mychatapplication.screens.splashScreen.mvp

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.example.mychatapplication.R
import kotlinx.android.synthetic.main.splash_activity.view.*


class SplashScreenView(context: Context) : FrameLayout(context) {

  init {
    inflate(getContext(), R.layout.splash_activity, this)
    iv_logo.startAnimation(
      AnimationUtils.loadAnimation(context,
      R.anim.splash_in
    ))
  }

  fun setLogoGone()
  {
    iv_logo.visibility = View.GONE
  }

  fun startAnimation() {
    iv_logo.startAnimation(
              AnimationUtils.loadAnimation(context,
              R.anim.splash_out
          ))
  }
}
