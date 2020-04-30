package com.github.reactnativehero.shadow

import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.ThemedReactContext

class ShadowView(private val reactContext: ThemedReactContext) : View(reactContext) {

    private val density: Float by lazy {
        reactContext.resources.displayMetrics.density
    }

    var bgColor = Color.TRANSPARENT

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    var borderRadius = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    var shadowColor = Color.BLACK

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    var shadowOffsetX = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    var shadowOffsetY = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    var shadowRadius = 0

        set(value) {
            if (field == value) {
                return
            }
            field = value
            applyShadow()
        }

    private fun applyShadow() {
        ShadowDrawable.setShadowDrawable(
            this,
            bgColor,
            (borderRadius * density).toInt(),
            shadowColor,
            (shadowRadius * density).toInt(),
            (shadowOffsetX * density).toInt(),
            (shadowOffsetY * density).toInt()
        )
    }

}