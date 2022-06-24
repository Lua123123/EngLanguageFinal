package com.example.englanguage.exoviewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class VerticalViewPager : ViewPager {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        setPageTransformer(true, ViewPagerTransform())
        overScrollMode = OVER_SCROLL_NEVER
    }

    private fun SwapXY(event: MotionEvent): MotionEvent {
        val x = width.toFloat()
        val y = height.toFloat()
        val newX = event.y / y * y
        val newY = event.x / x * x
        event.setLocation(newX, newY)
        return event
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercept = super.onInterceptTouchEvent(SwapXY(ev))
        SwapXY(ev)
        return intercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(SwapXY(ev))
    }

    class ViewPagerTransform : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            if (position < -1) {
                page.alpha = 0f
            } else if (position <= 0) {
                page.alpha = 1f
                page.translationX = page.width * -position
                page.translationY = page.height * position
                page.scaleX = 1f
                page.scaleY = 1f
            } else if (position <= 1) {
                page.alpha = 1 - position
                page.translationX = page.width * -position
                page.translationY = 0f
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(position))
                page.scaleX = scaleFactor
                page.scaleY = scaleFactor
            } else if (position > 1) {
                page.alpha = 0f
            }
        }

        companion object {
            private const val MIN_SCALE = 0.65f
        }
    }
}